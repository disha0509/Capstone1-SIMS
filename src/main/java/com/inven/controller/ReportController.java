package com.inven.controller;

import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inven.model.StockMovement;
import com.inven.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity<List<StockMovement>> getDailyReport() {
        return ResponseEntity.ok(reportService.generateDailyReport());
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<StockMovement>> getWeeklyReport() {
        return ResponseEntity.ok(reportService.generateWeeklyReport());
    }

    @GetMapping("/export-daily")
    public void exportDailyReport(HttpServletResponse response) throws IOException {
        List<StockMovement> dailyReport = reportService.generateDailyReport();
        writeReportToCsv(response, dailyReport, "daily-report.csv");
    }

    @GetMapping("/export-weekly")
    public void exportWeeklyReport(HttpServletResponse response) throws IOException {
        List<StockMovement> weeklyReport = reportService.generateWeeklyReport();
        writeReportToCsv(response, weeklyReport, "weekly-report.csv");
    }

    // Helper method to write CSV
    private void writeReportToCsv(HttpServletResponse response, List<StockMovement> reportData, String fileName) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Inventory ID,Movement Type,Quantity,Timestamp");

        for (StockMovement movement : reportData) {
            writer.println(movement.getId() + "," +
                        movement.getInventoryItem().getId() + "," +
                        movement.getMovementType() + "," +
                        movement.getQuantity() + "," +
                        movement.getTimestamp());
        }

        writer.flush();
    }
}

