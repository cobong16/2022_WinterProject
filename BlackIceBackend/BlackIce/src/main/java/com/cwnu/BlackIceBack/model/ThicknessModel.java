package com.cwnu.BlackIceBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThicknessModel {

    // 시간대 UTC +0로 저장됨
    //private String time;
    private String geopotential700hPa;
    private String geopotential850hPa;
    private String geopotential1000hPa;

}
