package org.example.domain.boundaries.out;

import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;

import java.util.List;

public interface ForeignInvoiceRepository {
    boolean createInvoice(ForeignInvoice invoice);
    boolean deleteInvoice(ForeignInvoice invoice);
    boolean updateInvoice(ForeignInvoice invoice);
    int getTotalAmountOfInvoice();
    double getAveragePriceOfInvoices();
    int getTotalAmountOfInvoiceByMonth(int month);
    List<ForeignInvoice> findInvoices(String query);

    List<ForeignInvoice> getInvoices();

    boolean deleteInvoiceById(int invoiceId);

    ForeignInvoice getInvoiceById(int invoiceId);
    List<Object[]> countInvoicesByMonth();
}
