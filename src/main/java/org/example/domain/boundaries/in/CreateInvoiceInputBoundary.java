package org.example.domain.boundaries.in;

import org.example.domain.entities.models.RequestModel;

public interface CreateInvoiceInputBoundary {
    void createInvoice(RequestModel req);
}
