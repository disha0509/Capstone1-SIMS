package com.inven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inven.model.InventoryItem;
import com.inven.service.AlertService;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryItem>> getLowStockAlerts() {
        return ResponseEntity.ok(alertService.getLowStockItems());
    }

    @GetMapping("/expiry")
    public ResponseEntity<List<InventoryItem>> getExpiryAlerts() {
        return ResponseEntity.ok(alertService.getExpiringItems());
    }
}
