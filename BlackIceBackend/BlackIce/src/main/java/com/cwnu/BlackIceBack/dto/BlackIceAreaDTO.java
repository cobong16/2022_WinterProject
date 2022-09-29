package com.cwnu.BlackIceBack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlackIceAreaDTO {
    private String roadName; // 도로명
    private String roadType; // 도로 종류
    private String area; // 지역명
    private String latitude; // 위도
    private String longitude; // 경도
    private String temperature; // 온도
    private String rainHour; // 강수량
    private String blackIce; // 블랙아이스 위험도
}
