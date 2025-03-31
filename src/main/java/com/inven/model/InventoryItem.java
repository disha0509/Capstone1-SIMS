package com.inven.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    private LocalDate expiryDate;

    private String category;

    private String supplierInfo;

    private int lowStockThreshold = 10; // Default value for low stock alerts

    private int expiryAlertDays = 7; // Default value for expiry alerts

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public InventoryItem() {
    }

    // Parameterized Constructor
    public InventoryItem(String name, int quantity, LocalDate expiryDate, String category, String supplierInfo) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.category = category;
        this.supplierInfo = supplierInfo;
        this.lowStockThreshold = 10;
        this.expiryAlertDays = 7;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(String supplierInfo) {
        this.supplierInfo = supplierInfo;
    }
    
    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public int getExpiryAlertDays() {
        return expiryAlertDays;
    }

    public void setExpiryAlertDays(int expiryAlertDays) {
        this.expiryAlertDays = expiryAlertDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                ", category='" + category + '\'' +
                ", supplierInfo='" + supplierInfo + '\'' +
                ", lowStockThreshold=" + lowStockThreshold +
                ", expiryAlertDays=" + expiryAlertDays +
                ", createdAt=" + createdAt +
                '}';
    }

}
