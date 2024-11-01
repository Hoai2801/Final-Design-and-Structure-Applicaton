package org.example.domain.boundaries.out;

import org.example.domain.entities.ForeignInvoice;

import java.util.List;

public interface ForeignInvoiceRepository {
    boolean createInvoice(ForeignInvoice invoice);
    boolean deleteInvoice(ForeignInvoice invoice);
    boolean updateInvoice(ForeignInvoice invoice);
    int getAmountOfInvoices();
    double getAveragePriceOfInvoices();
    int getTotalAmountOfInvoiceByMonth(int month);
    List<ForeignInvoice> findInvoices(String query);

    List<ForeignInvoice> getInvoices();
}
