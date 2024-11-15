package org.example.domain.entities;

import java.time.LocalDate;

public abstract class Invoice {
    private int invoiceId;
    private int customerId;
    private String fullName;
    private LocalDate invoiceDate;
    private double quantity; // KW consumption
    private double price;

    public Invoice(int invoiceId, int customerId, String fullName, LocalDate invoiceDate, double quantity, double price) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.price = price;
    }

    public Invoice() {
    }
    
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public int getInvoiceId() {
        return invoiceId;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculateTotal();
}
