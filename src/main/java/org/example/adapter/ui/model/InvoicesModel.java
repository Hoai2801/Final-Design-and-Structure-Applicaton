package org.example.adapter.ui.model;

public class InvoicesModel {
    private String customerId;
    private String fullName;
    private String invoiceDate;
    private String customerType;
    private String quantity; // KW consumption
    private String price;
    private String nationality;
    private int quota; 
    private double total; 

    public InvoicesModel(String customerId, String fullName, String invoiceDate, String customerType, String quantity, String price,
                         String nationality, int quota, double total) {
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public int getQuota() {
        return quota;
    }
    
    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getQuantity() {
        return quantity;
    }
    
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
        return "InvoicesModel{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
