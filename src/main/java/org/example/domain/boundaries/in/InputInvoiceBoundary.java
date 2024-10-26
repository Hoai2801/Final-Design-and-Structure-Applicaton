package org.example.domain.boundaries.in;

import org.example.domain.entities.models.RequestModel;

public interface InputInvoiceBoundary {
    void createInvoice(RequestModel req);
    void deleteInvoice(RequestModel req);
    void updateInvoice(RequestModel req);
    void getTotalAmountOfInvoice(int type);
    void getTotalAmountOfInvoiceByMonth(int month);
    void getInvoices();
}
