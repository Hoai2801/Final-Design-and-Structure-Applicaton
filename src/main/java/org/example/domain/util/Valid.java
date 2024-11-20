package org.example.domain.util;

import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;

import java.time.LocalDate;

public class Valid {

    public static ValidResult valid(RequestModel req) {
        if (req.getInvoiceId() < 0) { // when create invoice, invoiceId = 0
            return new ValidResult(false, "Invoice ID is required");
        }
        if (req.getCustomerId() <= 0) {
            return new ValidResult(false, "Customer ID is required");
        }
        if (req.getFullName().isEmpty()) {
            return new ValidResult(false, "Full name is required");
        }
        if (req.getInvoiceDate() == null) {
            return new ValidResult(false, "Invoice date is required");
        }
        if (req.getInvoiceDate().isAfter(LocalDate.now())) {
            return new ValidResult(false, "Invoice date must be in the past");
        }
        if (req.getQuantity() <= 0) {
            return new ValidResult(false, "Quantity must be greater than 0");
        }
        if (req.getPrice() <= 0) {
            return new ValidResult(false, "Price must be greater than 0");
        }
        if (req.getNationality().isEmpty()) {
            return new ValidResult(false, "Nationality is required");
        }
        if (req.getNationality().equals("Vietnam")) {
            if (req.getQuota() <= 0) {
                return new ValidResult(false, "Quota must be greater than 0");
            }
        } 
        return new ValidResult(true, ""); 
    }
}
