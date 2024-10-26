package org.example.domain.entities.dtos;

import java.time.LocalDate;

public class InvoiceDTO {
    private int customerId;
    private String fullName;
    private LocalDate invoiceDate;
    private int quantity; // KW consumption
    private String customerType;
    private int price;
    private String nationality;
    private int quota;
    private double total;

    public InvoiceDTO(int customerId, String fullName, LocalDate invoiceDate, String customerType,int quantity, int price, String nationality, int quota, double total) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuota() {
        return quota;
    }
    
    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
