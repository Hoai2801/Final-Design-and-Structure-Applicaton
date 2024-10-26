package org.example.domain.entities;

import java.time.LocalDate;

public class ForeignInvoice extends Invoice {
    private String nationality;
    public ForeignInvoice(int customerId, String fullName, String nationality, LocalDate invoiceDate, int quantity, int price) {
        super(customerId, fullName, invoiceDate, quantity, price);
        this.nationality = nationality;
    }

    public ForeignInvoice(String fullName, String nationality, LocalDate invoiceDate, int quantity, int price) {
        super(fullName, invoiceDate, quantity, price);
        this.nationality = nationality;
    }
    
    public ForeignInvoice() {
        super();
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public double calculateTotal() {
        return super.getQuantity() * super.getPrice();
    }
}
