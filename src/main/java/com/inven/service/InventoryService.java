package com.inven.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.model.InventoryItem;
import com.inven.model.StockMovement;
import com.inven.repository.InventoryItemRepository;
import com.inven.repository.StockMovementRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);

    }

    public InventoryItem editItem(Long id, InventoryItem updatedItem) {
        InventoryItem existingItem = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(updatedItem.getName());
        existingItem.setQuantity(updatedItem.getQuantity());
        existingItem.setExpiryDate(updatedItem.getExpiryDate());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setSupplierInfo(updatedItem.getSupplierInfo());
        return inventoryItemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        inventoryItemRepository.deleteById(id);
    }

    public List<InventoryItem> getAllItems() {
        return inventoryItemRepository.findAll();
    }

    public void recordStockMovement(Long inventoryId, String movementType, int quantity) {
        InventoryItem item = inventoryItemRepository.findById(inventoryId)
            .orElseThrow(() -> new RuntimeException("Item not found"));

        StockMovement movement = new StockMovement();
        movement.setInventoryItem(item);
        movement.setMovementType(movementType);
        movement.setQuantity(quantity);
        movement.setTimestamp(LocalDateTime.now());
        stockMovementRepository.save(movement);
    }
    public void sellItem(Long id, int quantity) {
        InventoryItem item = inventoryItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }
        item.setQuantity(item.getQuantity() - quantity);
        inventoryItemRepository.save(item);
        recordStockMovement(id, "SELL", quantity);
    }
}

