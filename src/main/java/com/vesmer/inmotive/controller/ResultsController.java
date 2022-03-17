package com.vesmer.inmotive.controller;

import com.vesmer.inmotive.service.CalculationAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculation")
public class ResultsController {
    private final CalculationAsyncService calcAsyncService;

    @Autowired
    public ResultsController(CalculationAsyncService calcAsyncService) {
        this.calcAsyncService = calcAsyncService;
    }
}
