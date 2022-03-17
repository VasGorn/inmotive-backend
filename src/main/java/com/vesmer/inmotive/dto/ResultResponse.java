package com.vesmer.inmotive.dto;

import com.vesmer.inmotive.model.CalculationStatus;
import com.vesmer.inmotive.model.ResultChartData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private ResultChartData[] chartsData;
    private CalculationStatus status;
}
