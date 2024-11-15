package org.example.domain.entities;

import java.time.LocalDate;

public class ForeignInvoice extends Invoice {
    private String nationality;
    
    public ForeignInvoice(int invoiceId, int customerId, String fullName, LocalDate invoiceDate, double quantity, double price, String nationality) {
        super(invoiceId, customerId, fullName, invoiceDate, quantity, price);
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
