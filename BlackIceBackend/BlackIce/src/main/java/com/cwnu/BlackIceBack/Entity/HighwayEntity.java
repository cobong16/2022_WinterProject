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
@Table(name = "highway_info")
public class HighwayEntity {

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
}
