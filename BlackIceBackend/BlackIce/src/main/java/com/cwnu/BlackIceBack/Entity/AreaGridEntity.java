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
@Table(name = "area_grid")
public class AreaGridEntity {

    @Id
    @Column(name = "gridKey")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gridKey;

    @Column(name = "gridX")
    private int gridX;

    @Column(name = "gridY")
    private int gridY;

}
