package org.example.domain.entities;

import java.time.LocalDate;

public class VietnameseInvoice extends Invoice {
    private String customerType; // sinh hoat, kinh doanh, san xuat
    private double quota;

    public VietnameseInvoice(int invoiceId, int customerId, String fullName, LocalDate invoiceDate, double quantity, double price, String customerType, double quota) {
        super(invoiceId, customerId, fullName, invoiceDate, quantity, price);
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

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    @Override
    public double calculateTotal() {
        if (super.getQuantity() <= quota) {
            return super.getQuantity() * super.getPrice();
        } else {
            double excessQuantity = super.getQuantity() - quota;
            return (quota * super.getPrice()) + (excessQuantity * super.getPrice() * 2.5);
        }
    }
}
