package org.example.domain.boundaries.in;

public interface GetInvoiceByIdInputBoundary {
    void getInvoiceById(int invoiceId, String type);
}
