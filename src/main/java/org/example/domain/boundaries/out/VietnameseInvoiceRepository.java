package org.example.domain.boundaries.out;

import org.example.domain.entities.VietnameseInvoice;

import java.util.List;

public interface VietnameseInvoiceRepository {
    boolean createInvoice(VietnameseInvoice invoice);
    boolean deleteInvoice(VietnameseInvoice invoice);
    boolean updateInvoice(VietnameseInvoice invoice);
    int getTotalAmountOfInvoice();
    int getTotalAmountOfInvoiceByMonth(int month);
    List<VietnameseInvoice> findInvoices(String query);

    List<VietnameseInvoice> getInvoices();
}
