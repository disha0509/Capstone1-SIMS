package com.inven.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.model.InventoryItem;
import com.inven.repository.InventoryItemRepository;

@Service
public class AlertService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    public List<InventoryItem> getLowStockItems() {
        return inventoryItemRepository.findAll()
            .stream()
            .filter(item -> item.getQuantity() <= item.getLowStockThreshold())
            .collect(Collectors.toList());
    }

    public List<InventoryItem> getExpiringItems() {
        LocalDate today = LocalDate.now();
        return inventoryItemRepository.findAll()
            .stream()
            .filter(item -> item.getExpiryDate() != null && 
                            item.getExpiryDate().isBefore(today.plusDays(item.getExpiryAlertDays())))
            .collect(Collectors.toList());
    }
}