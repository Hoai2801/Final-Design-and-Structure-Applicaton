package org.example.domain.boundaries.out;

import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ListInvoiceResponseModel;

import java.util.List;

public interface GetListInvoicesOutputBoundary {
    void getListInvoices(List<InvoiceDTO> invoices);
}
