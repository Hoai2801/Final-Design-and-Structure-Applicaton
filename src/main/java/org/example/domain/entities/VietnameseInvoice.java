package org.example.domain.entities;

import java.time.LocalDate;

public class VietnameseInvoice extends Invoice {
    private String customerType; // sinh hoat, kinh doanh, san xuat
    private int quota;
    
    public VietnameseInvoice(int customerId, String fullName, LocalDate invoiceDate, String customerType, int quantity, int price, int quota) {
        super(customerId, fullName, invoiceDate, quantity, price);
        this.customerType = customerType;
        this.quota = quota;
    }

    public VietnameseInvoice(String fullName, LocalDate invoiceDate, String customerType, int quantity, int price, int quota) {
        super(fullName, invoiceDate, quantity, price);
        this.customerType = customerType;
        this.quota = quota;
    }

    public VietnameseInvoice() {
        super();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    @Override
    public double calculateTotal() {
        if (super.getQuantity() <= quota) {
            return super.getQuantity() * super.getPrice();
        } else {
            int excessQuantity = super.getQuantity() - quota;
            return (quota * super.getPrice()) + (excessQuantity * super.getPrice() * 2.5);
        }
    }
}
