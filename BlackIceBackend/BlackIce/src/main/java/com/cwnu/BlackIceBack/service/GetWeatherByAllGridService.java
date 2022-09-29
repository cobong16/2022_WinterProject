package com.cwnu.BlackIceBack.service;

import com.cwnu.BlackIceBack.Entity.AreaGridEntity;
import com.cwnu.BlackIceBack.Entity.HighwayGridEntity;
import com.cwnu.BlackIceBack.Persistence.AreaGridRepository;
import com.cwnu.BlackIceBack.Persistence.HighwayGridRepository;
import com.cwnu.BlackIceBack.model.GridXYModel;
import com.cwnu.BlackIceBack.model.WeatherModel;
import com.cwnu.BlackIceBack.serviceAPI.GetWeatherByGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GetWeatherByAllGridService {

    @Autowired
    private AreaGridRepository areaGridRepository;

    @Autowired
    private GetWeatherByGrid getWeatherByGrid;

    @Autowired
    private HighwayGridRepository highwayGridRepository;

    private final List<WeatherModel> weatherModels = new ArrayList<>();

    public List<WeatherModel> getAreaWeatherInfo() throws Exception {

        // DB 에서 상습결빙지역에 해당하는 grid 좌표계 받아오기
        List<AreaGridEntity> areaGridEntities = areaGridRepository.findAll();

        int progress = 0;
        int length = areaGridEntities.size();

        // 모든 그리드 좌표계의 날씨 정보 조회하기
        for (AreaGridEntity entity : areaGridEntities) {
            WeatherModel weatherModel = getWeatherByGrid.getInfo(GridXYModel.builder()
                    .gridX(entity.getGridX()).gridY(entity.getGridY())
                    .build());

            weatherModels.add(weatherModel);

            progress++;
            System.out.println("WeatherModel: total " + progress + " of " + length + " has been saved.");
        }

        return weatherModels;
    }

    public List<WeatherModel> getHighwayWeatherInfo() throws Exception {

        List<HighwayGridEntity> highwayGridEntities = highwayGridRepository.findAll();

        int progress = 0;
        int length = highwayGridEntities.size();

        for (HighwayGridEntity entity : highwayGridEntities) {
            WeatherModel weatherModel = getWeatherByGrid.getInfo(GridXYModel.builder()
                    .gridX(entity.getGridX()).gridY(entity.getGridY())
                    .build());

            weatherModels.add(weatherModel);

            progress++;
            System.out.println("WeatherModel: total " + progress + " of " + length + " has been saved.");
        }

        return weatherModels;
    }
}
