package com.inven.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.model.StockMovement;
import com.inven.repository.StockMovementRepository;

@Service
public class ReportService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    // Generate daily report
    public List<StockMovement> generateDailyReport() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        return stockMovementRepository.findAllByTimestampBetween(startOfDay, endOfDay);
    }

    // Generate weekly report
    public List<StockMovement> generateWeeklyReport() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        LocalDateTime startOfWeekTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekTime = today.atTime(LocalTime.MAX);

        return stockMovementRepository.findAllByTimestampBetween(startOfWeekTime, endOfWeekTime);
    }
}
