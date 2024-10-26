package org.example.domain.entities;

import java.time.LocalDate;

public abstract class Invoice {
    private int customerId;
    private String fullName;
    private LocalDate invoiceDate;
    private int quantity; // KW consumption
    private int price;

    public Invoice(int customerId, String fullName, LocalDate invoiceDate, int quantity, int price) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Invoice(String fullName, LocalDate invoiceDate, int quantity, int price) {
        this.fullName = fullName;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.price = price;
    }

    public Invoice() {
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract double calculateTotal();
}
