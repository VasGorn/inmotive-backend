package com.vesmer.inmotive.controller;

import com.vesmer.inmotive.dto.ResultResponse;
import com.vesmer.inmotive.exception.InmotiveException;
import com.vesmer.inmotive.model.CalculationStatus;
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
}
