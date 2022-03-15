package com.vesmer.inmotive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CalculationStatus {
    private boolean isRunning;
    private int progress;
    private ResultChartData chartsData;
}
