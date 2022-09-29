package com.cwnu.BlackIceBack.service;

import com.cwnu.BlackIceBack.Entity.HighwayEntity;
import com.cwnu.BlackIceBack.Persistence.HighwayRepository;
import com.cwnu.BlackIceBack.dto.BlackIceAreaDTO;
import com.cwnu.BlackIceBack.model.LocationModel;
import com.cwnu.BlackIceBack.model.ThicknessModel;
import com.cwnu.BlackIceBack.model.TroposTempModel;
import com.cwnu.BlackIceBack.model.WeatherModel;
import com.cwnu.BlackIceBack.serviceAPI.GetGeopotentialThickness;
import com.cwnu.BlackIceBack.serviceAPI.GetTroposphereTemp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CalBlackIceByHighwayService {

    @Autowired
    private HighwayRepository highwayRepository;

    @Autowired
    private GetWeatherByAllGridService getWeatherByAllGridService;

    @Autowired
    private GetGeopotentialThickness getGeopotentialThickness;

    @Autowired
    private GetTroposphereTemp getTroposphereTemp;

    private final List<BlackIceAreaDTO> blackIceAreaDTOS = new ArrayList<>();

    @Cacheable(value = "highway")
    public List<BlackIceAreaDTO> calInfo() throws Exception {

        List<WeatherModel> weatherModels = getWeatherByAllGridService.getHighwayWeatherInfo();

        List<HighwayEntity> highwayEntities = highwayRepository.findAll();

        int progress = 0;
        int length = highwayEntities.size();

        for (HighwayEntity entity : highwayEntities) {

            // 기상청 강수 정보 받아오기
            WeatherModel weatherModel = searchGridModel(weatherModels, entity.getGridX(), entity.getGridY());

            // 대기압 온도 받아오기
            TroposTempModel tempModel = getTroposphereTemp.getInfo(LocationModel.builder()
                    .latitude(Double.toString(entity.getLatitude()))
                    .longitude(Double.toString(entity.getLongitude()))
                    .build());

            // 대기압 유량 받아오기
            ThicknessModel thicknessModel = getGeopotentialThickness.getInfo(LocationModel.builder()
                    .latitude(Double.toString(entity.getLatitude()))
                    .longitude(Double.toString(entity.getLongitude()))
                    .build());

            // weatherModel 에서의 강수량과 강수형태
            int rainType = Integer.parseInt(weatherModel.getPTY());
            double rainHour = Double.parseDouble(weatherModel.getRN1());
            double groundTemp = Double.parseDouble(weatherModel.getT1H());

            BlackIceAreaDTO blackIceAreaDTO = new BlackIceAreaDTO();

            blackIceAreaDTO.setLatitude(String.valueOf(entity.getLatitude()));
            blackIceAreaDTO.setLongitude(String.valueOf(entity.getLongitude()));

            blackIceAreaDTO.setRoadName(entity.getRoadName());
            blackIceAreaDTO.setRoadType(entity.getRoadType());
            blackIceAreaDTO.setArea(entity.getArea());

            blackIceAreaDTO.setTemperature(weatherModel.getT1H());
            blackIceAreaDTO.setRainHour(weatherModel.getRN1());

            double geo1000 = Double.parseDouble(thicknessModel.getGeopotential1000hPa());
            double temp1000 = Double.parseDouble(tempModel.getTemperature1000hPa());

            if (rainType == -1 || geo1000 == -999 || temp1000 == -999) {
                blackIceAreaDTO.setBlackIce("-1");
                blackIceAreaDTOS.add(blackIceAreaDTO);
                break;
            }

            // 강수량 확인
            if (rainHour > 0 || rainType != 0) {
                // 지면 온도 확인
                if (groundTemp <= 0) {
                    // 상층 역전 및 상층 영상 기존 층 확인
                    if (checkWarmLayer(tempModel)) {
                        // 아래 층후가 조건을 만족하는지 확인
                        if (lowerThickness(thicknessModel)) {
                            // 위 층후가 조건을 만족하는지 확인
                            if (upperThickness(thicknessModel)) {
                                blackIceAreaDTO.setBlackIce("2");
                            } else {
                                blackIceAreaDTO.setBlackIce("1");
                            }
                        } else {
                            blackIceAreaDTO.setBlackIce("0");
                        }
                    } else {
                        blackIceAreaDTO.setBlackIce("0");
                    }
                } else {
                    blackIceAreaDTO.setBlackIce("0");
                }
            } else {
                blackIceAreaDTO.setBlackIce("0");
            }

            blackIceAreaDTOS.add(blackIceAreaDTO);

            progress++;
            System.out.println("Highway: total " + progress + " of " + length + " has been saved.");
        }

        return blackIceAreaDTOS;
    }

    public WeatherModel searchGridModel(List<WeatherModel> weatherModels, int gridX, int gridY) {

        for (WeatherModel model : weatherModels) {
            if (gridX == model.getGridX() && gridY == model.getGridY()) {
                return  model;
            }
        }
        return null;
    }

    // 상층 기온이 역전되어있으며 양을 가지는 고도가 존재하는지 확인하기
    private boolean checkWarmLayer(TroposTempModel tempModel) {
        return Double.parseDouble(tempModel.getTemperature1000hPa()) <=
                Double.parseDouble(tempModel.getTemperature850hPa()) &&
                (Double.parseDouble(tempModel.getTemperature850hPa()) >= 0 ||
                        Double.parseDouble(tempModel.getTemperature700hPa()) >= 0);
    }

    // 위쪽 층후가 어는 비의 조건을 만족하는지 확인하기
    private boolean upperThickness(ThicknessModel thicknessModel) {
        double thickness = Double.parseDouble(thicknessModel.getGeopotential700hPa()) -
                Double.parseDouble(thicknessModel.getGeopotential850hPa());

        return thickness >= 1540.0;
    }

    // 아래 층후가 어는 비의 조건을 만족하는지 확인하기
    private boolean lowerThickness(ThicknessModel thicknessModel) {
        double thickness = Double.parseDouble(thicknessModel.getGeopotential850hPa()) -
                Double.parseDouble(thicknessModel.getGeopotential1000hPa());

        return thickness >= 1290.0 && thickness <= 1310.0;

    }

    @Cacheable(value = "highway")
    public List<BlackIceAreaDTO> getInfo() {
        return blackIceAreaDTOS;
    }

    @CacheEvict(value = "highway", allEntries = true)
    public void wipeInfo() {
        blackIceAreaDTOS.clear();
    }

}
