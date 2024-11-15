package org.example.domain.entities.models;

import java.time.LocalDate;

public class RequestModel {
    private int invoiceId; 
    private int customerId;
    private String fullName;
    private String customerType;
    private String nationality;
    private LocalDate invoiceDate;
    private double quantity;
    private double price;
    private double quota;

    public RequestModel(int invoiceId, int customerId, String fullName, String customerType, String nationality, LocalDate invoiceDate, double quantity, double price, double quota) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.customerType = customerType;
        this.nationality = nationality;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.price = price;
        this.quota = quota;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }
}
