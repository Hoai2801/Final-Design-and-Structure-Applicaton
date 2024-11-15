package org.example.domain.boundaries.in;

public interface DeleteInvoiceByIdInputBoundary {
    void deleteInvoiceById(int invoiceId, String type);
}
