package org.example.domain.boundaries.out;

import org.example.domain.entities.dtos.InvoiceDTO;

import java.util.List;

public interface SearchInvoiceByNameOutputBoundary {
    void showSearchInvoiceByName(List<InvoiceDTO> invoices);
}
