/*
위경도를 입력 받아 해당하는 위경도의 어는 비 위험도를 반환하는 클래스
 */
package com.cwnu.BlackIceBack.service;

import com.cwnu.BlackIceBack.dto.BlackIceDTO;
import com.cwnu.BlackIceBack.dto.LocationDTO;
import com.cwnu.BlackIceBack.model.LocationModel;
import com.cwnu.BlackIceBack.model.ThicknessModel;
import com.cwnu.BlackIceBack.model.TroposTempModel;
import com.cwnu.BlackIceBack.model.WeatherModel;
import com.cwnu.BlackIceBack.serviceAPI.GetGeopotentialThickness;
import com.cwnu.BlackIceBack.serviceAPI.GetTroposphereTemp;
import com.cwnu.BlackIceBack.serviceAPI.GetWeatherByLatLng;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalBlackIceByLatLngService {

    @Autowired
    private GetWeatherByLatLng weather;

    @Autowired
    private GetGeopotentialThickness getGeopotentialThickness;

    @Autowired
    private GetTroposphereTemp getTroposphereTemp;

    public BlackIceDTO calInfo(LocationDTO locationDTO) throws Exception {

        LocationModel locationModel = new LocationModel(locationDTO.getLatitude(), locationDTO.getLongitude());

        // 기온 및 강수량
        WeatherModel weatherModel = weather.getInfo(locationModel);
        // 대기압 별 온도
        TroposTempModel tempModel = getTroposphereTemp.getInfo(locationModel);
        // 대기압 별 유량
        ThicknessModel thicknessModel = getGeopotentialThickness.getInfo(locationModel);

        // weatherModel 에서의 강수량과 강수형태
        int rainType = Integer.parseInt(weatherModel.getPTY());
        double rainHour = Double.parseDouble(weatherModel.getRN1());
        double groundTemp = Double.parseDouble(weatherModel.getT1H());

        BlackIceDTO blackIceDTO = new BlackIceDTO();
        blackIceDTO.setLatitude(locationModel.getLatitude());
        blackIceDTO.setLongitude(locationModel.getLongitude());
        blackIceDTO.setTemperature(weatherModel.getT1H());
        blackIceDTO.setRainHour(weatherModel.getRN1());

        if (rainType == -1) {
            blackIceDTO.setBlackIce("-1");
            return blackIceDTO;
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
                            blackIceDTO.setBlackIce("2");
                        } else {
                            blackIceDTO.setBlackIce("1");
                        }
                    } else {
                        blackIceDTO.setBlackIce("0");
                    }
                } else {
                    blackIceDTO.setBlackIce("0");
                }
            } else {
                blackIceDTO.setBlackIce("0");
            }
        } else {
            blackIceDTO.setBlackIce("0");
        }

        return blackIceDTO;
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
}
