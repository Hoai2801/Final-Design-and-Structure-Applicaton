package org.example.domain.entities.models;

import java.time.LocalDate;

public class RequestModel {

    private int customerId;
    private String fullName;
    private String customerType;
    private String nationality;
    private LocalDate invoiceDate;
    private int quantity;
    private int price;
    private int quota;

    public RequestModel(int customerId, String fullName, String customerType, String nationality, LocalDate invoiceDate, int quantity, int price, int quota) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.customerType = customerType;
        this.nationality = nationality;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.price = price;
        this.quota = quota;
    }

    public RequestModel() {
        
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

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
