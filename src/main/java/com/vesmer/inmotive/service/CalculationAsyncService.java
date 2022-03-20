package com.vesmer.inmotive.service;

import com.vesmer.inmotive.exception.InmotiveException;
import com.vesmer.inmotive.model.CalculationStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CalculationAsyncService {
    private final AtomicReference<ConcurrentHashMap<Long, CalculationStatus>>
            calcProgressMap = new AtomicReference<>(new ConcurrentHashMap<>());

    public boolean containsCalcEntry(Long projectId) {
        if (calcProgressMap.get() != null) {
            return calcProgressMap.get().containsKey(projectId);
        }
        return false;
    }

    public CalculationStatus getStatus(Long projectId) {
        CalculationStatus status = calcProgressMap.get().get(projectId);
        if (status == null) {
            status = new CalculationStatus(true, 0, null);
        }
        return status;
    }

    @Async
    public CompletableFuture<CalculationStatus> asyncManager(Long projectId) {
        CalculationStatus calcStatus =
                new CalculationStatus(true, 0, null);

        calcProgressMap.get().put(projectId, calcStatus);
        int progress = 0;
        while (progress < 100) {
            try {
                Thread.sleep(123);
            } catch (InterruptedException e) {
                throw new InmotiveException("Error in calculation thread: " + projectId);
            }
            progress += 1;
            calcStatus.setProgress(progress);
        }
        calcStatus.setRunning(false);
        return CompletableFuture.completedFuture(calcStatus);
    }
}
