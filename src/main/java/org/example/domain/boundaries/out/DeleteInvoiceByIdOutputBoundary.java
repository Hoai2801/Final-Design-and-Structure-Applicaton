package org.example.domain.boundaries.out;

import org.example.domain.entities.models.ResponseModel;

public interface DeleteInvoiceByIdOutputBoundary {

    void deleteInvoice(ResponseModel responseModel);
}
