package com.inven.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inven.model.InventoryItem;
import com.inven.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<InventoryItem> addItem(@RequestBody InventoryItem item) {
        return ResponseEntity.ok(inventoryService.addItem(item));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<InventoryItem> editItem(@PathVariable Long id, @RequestBody InventoryItem updatedItem) {
        return ResponseEntity.ok(inventoryService.editItem(id, updatedItem));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully");
    }

    @GetMapping("/view")
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    @PostMapping("/stock-movement/{id}")
    public ResponseEntity<String> recordStockMovement(
            @PathVariable Long id,
            @RequestParam String type,
            @RequestParam int quantity) {
        inventoryService.recordStockMovement(id, type, quantity);
        return ResponseEntity.ok("Stock movement recorded successfully");
    }

    @PostMapping("/sell/{id}")
public ResponseEntity<String> sellItem(@PathVariable Long id, @RequestParam int quantity) {
    inventoryService.sellItem(id, quantity);
    return ResponseEntity.ok("Item sold successfully");
}
}
