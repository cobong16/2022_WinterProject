package com.cwnu.BlackIceBack.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "area_info")
public class AreaEntity {

    @Id
    @Column(name = "sectorNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sectorNo;

    @Column(name = "institute")
    private String institute;

    @Column(name = "roadType")
    private String roadType;

    @Column(name = "area")
    private String area;

    @Column(name = "roadName")
    private String roadName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "gridX")
    private int gridX;

    @Column(name = "gridY")
    private int gridY;

    // TODO 엔터티 수정으로 인한 컬럼 추가 그에 따른 다른 코드 검증 필요
}
