package org.example.domain.entities.dtos;

import java.time.LocalDate;

public class InvoiceDTO {
    private int invoiceId; 
    private int customerId;
    private String fullName;
    private LocalDate invoiceDate;
    private double quantity; // KW consumption
    private String customerType;
    private double price;
    private String nationality;
    private double quota;
    private double total;

    public InvoiceDTO(int invoiceId, int customerId, String fullName, LocalDate invoiceDate, String customerType, double quantity, double price, String nationality, double quota, double total) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.invoiceDate = invoiceDate;
        this.quantity = quantity;
        this.customerType = customerType;
        this.price = price;
        this.nationality = nationality;
        this.quota = quota;
        this.total = total;
    }
    
    public InvoiceDTO() {
    }
    
    public int getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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
    
    public String getCustomerType() {
        return customerType;
    }
    
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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
    
    public double getQuota() {
        return quota;
    }
    
    public void setQuota(double quota) {
        this.quota = quota;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public double getTotal() {
        return total;
    } 

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", quantity=" + quantity +
                ", price=" + price +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
