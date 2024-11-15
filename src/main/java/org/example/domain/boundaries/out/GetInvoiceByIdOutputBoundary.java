package org.example.domain.boundaries.out;

import org.example.domain.entities.dtos.InvoiceDTO;

public interface GetInvoiceByIdOutputBoundary {
    void getInvoiceById(InvoiceDTO invoice);
}
