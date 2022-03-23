package com.vesmer.inmotive.controller;

import com.vesmer.inmotive.dto.ResultResponse;
import com.vesmer.inmotive.exception.InmotiveException;
import com.vesmer.inmotive.model.CalculationStatus;
import com.vesmer.inmotive.model.Point;
import com.vesmer.inmotive.model.ResultChartData;
import com.vesmer.inmotive.service.CalculationAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/calculation")
public class ResultsController {
    private final CalculationAsyncService calcAsyncService;

    @Autowired
    public ResultsController(CalculationAsyncService calcAsyncService) {
        this.calcAsyncService = calcAsyncService;
    }

    @GetMapping("/run/{projectId}")
    public ResponseEntity<ResultResponse> runCalculation(@PathVariable Long projectId) {
        boolean isContains = calcAsyncService.containsCalcEntry(projectId);
        if (!isContains) {
            calcAsyncService.asyncManager(projectId);
        }
        CalculationStatus status = calcAsyncService.getStatus(projectId);
        ResultResponse response = new ResultResponse();
        response.setStatus(status);
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping("/status/{projectId}")
    public ResponseEntity<ResultResponse> calculationStatus(@PathVariable Long projectId) {
        boolean isContains = calcAsyncService.containsCalcEntry(projectId);
        if (!isContains) {
            throw new InmotiveException("Calculation not running in project" +
                    " id: " + projectId);
        }
        CalculationStatus calcStatus = calcAsyncService.getStatus(projectId);
        ResultChartData[] chartData = null;
        if (!calcStatus.isRunning()) {
            calcAsyncService.removeCalc(projectId);
            chartData = inMemoryChartData();
        }

        ResultResponse response = new ResultResponse();
        response.setStatus(calcStatus);
        response.setChartsData(chartData);

        return ResponseEntity.status(OK).body(response);
    }

    private ResultChartData[] inMemoryChartData() {
        Point point11 = new Point(250, 25);
        Point point12 = new Point(500, 22.5);
        Point point13 = new Point(1500, 23);
        Point point14 = new Point(2250, 35);
        Point[] pointArr1 = {point11, point12, point13, point14};
        ResultChartData data1 = new ResultChartData("Current consumption by motor",
                "Speed, rpm", "Current consumption by motor, A", pointArr1);

        Point point21 = new Point(250, 3000);
        Point point22 = new Point(500, 4500);
        Point point23 = new Point(1500, 12000);
        Point point24 = new Point(2250, 20000);
        Point[] pointArr2 = {point21, point22, point23, point24};
        ResultChartData data2 = new ResultChartData("Power consumption by motor",
                "Speed, rpm", "Power consumption by motor, W", pointArr2);

        Point point31 = new Point(250, 0.6);
        Point point32 = new Point(500, 0.75);
        Point point33 = new Point(1500, 0.85);
        Point point34 = new Point(2250, 0.8);
        Point[] pointArr3 = {point31, point32, point33, point34};
        ResultChartData data3 = new ResultChartData("Motor efficiency",
                "Speed, rpm", "Motor efficiency, r.u.", pointArr3);

        return new ResultChartData[]{data1, data2, data3};
    }
}
