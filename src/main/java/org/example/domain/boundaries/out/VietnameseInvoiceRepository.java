package org.example.domain.boundaries.out;

import org.example.domain.entities.VietnameseInvoice;

import java.util.List;

public interface VietnameseInvoiceRepository {
    boolean createInvoice(VietnameseInvoice invoice);
    int getTotalAmountOfInvoice();
    int getTotalAmountOfInvoiceByMonth(int month);
    List<VietnameseInvoice> findInvoices(String name);

    List<VietnameseInvoice> getInvoices();

    boolean deleteInvoiceById(int invoiceId);

    VietnameseInvoice getInvoiceById(int invoiceId);

    boolean updateInvoice(VietnameseInvoice existingInvoice);
}
