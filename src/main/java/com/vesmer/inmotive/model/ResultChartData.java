package com.vesmer.inmotive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultChartData {
    private String name;
    private String xaxisName;
    private String yaxisName;
    private Point[] data;
}
