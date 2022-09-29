package com.cwnu.BlackIceBack.service;

import com.cwnu.BlackIceBack.model.GridXYModel;
import com.cwnu.BlackIceBack.model.LocationModel;
import org.springframework.stereotype.Service;

@Service
public class ConvertGPSService {

    private final double RE = 6371.00877; // 지구반경
    private final double GRID = 5.0; // 격자(그리드) 간격 (5.0km)
    private final double SLAT1 = 30.0; // 투영 위도1
    private final double SLAT2 = 60.0; // 투영 위도2
    private final double OLON = 126.0; // 기준점 경도
    private final double OLAT = 38.0; // 기준점 위도
    private final double XO = 43; // 기준점 X 그리드 좌표
    private final double YO = 136; // 기준점 Y 그리드 좌표

    public GridXYModel convertXY(LocationModel locationModel) {
        double latitude = Double.parseDouble(locationModel.getLatitude());
        double longitude = Double.parseDouble(locationModel.getLongitude());
        GridXYModel rs = new GridXYModel();
        double DEGRAD = Math.PI / 180.0;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);

        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;

        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        double ra = Math.tan(Math.PI * 0.25 + (latitude) * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);

        double theta = longitude * DEGRAD - olon;

        if (theta > Math.PI)
            theta -= 2.0 * Math.PI;

        if (theta < -Math.PI)
            theta += 2.0 * Math.PI;

        theta *= sn;

        rs.setGridX((int) Math.floor(ra * Math.sin(theta) + XO + 0.5));
        rs.setGridY((int) Math.floor(ro - ra * Math.cos(theta) + YO + 0.5));

        return rs;
    }

}
