package com.cwnu.BlackIceBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GridXYModel {

    private int gridX;
    private int gridY;

}
