package com.cwnu.BlackIceBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlackIceDTO {

    private String latitude;
    private String longitude;
    private String temperature;
    private String rainHour;
    private String blackIce;

}
