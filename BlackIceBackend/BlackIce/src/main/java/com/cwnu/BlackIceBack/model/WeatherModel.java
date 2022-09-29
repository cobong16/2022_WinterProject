package com.cwnu.BlackIceBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeatherModel {

    private int gridX;
    private int gridY;
    // 기온
    private String T1H;
    // 1시간 강수량
    private String RN1;
    // 강수형태 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
    private String PTY;

}
