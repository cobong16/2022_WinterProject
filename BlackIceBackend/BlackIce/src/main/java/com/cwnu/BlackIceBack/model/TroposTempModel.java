package com.cwnu.BlackIceBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TroposTempModel {

    // 시간대 UTC +0로 저장됨
    //private String time;
    private String temperature700hPa;
    private String temperature850hPa;
    private String temperature1000hPa;

}