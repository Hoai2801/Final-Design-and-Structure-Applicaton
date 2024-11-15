package org.example.domain.entities;

import java.time.LocalDate;

public class InvoiceFactory {
    public static Invoice createInvoice(int customerId, String fullName, LocalDate invoiceDate, String customerType, double quantity, double price, double quota, String nationality) {
        if (nationality.equals("Vietnam")) {
            return new VietnameseInvoice(0, customerId, fullName, invoiceDate, quantity, price, customerType, quota);
        } else {
            return new ForeignInvoice(0, customerId, fullName, invoiceDate, quantity, price, nationality);
        }
    }
}
