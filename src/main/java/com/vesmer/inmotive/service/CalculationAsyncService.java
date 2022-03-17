package com.vesmer.inmotive.service;

import com.vesmer.inmotive.model.CalculationStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CalculationAsyncService {
    private final AtomicReference<ConcurrentHashMap<Long, CalculationStatus>>
            calcProgressMap = new AtomicReference<>(new ConcurrentHashMap<>());
}
