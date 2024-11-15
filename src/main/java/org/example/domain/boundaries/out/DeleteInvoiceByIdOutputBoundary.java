package org.example.domain.boundaries.out;

import org.example.domain.entities.models.ResponseModel;

public interface DeleteInvoiceByIdOutputBoundary {
    void onDeleteSuccess(ResponseModel message);
    void onDeleteFail(ResponseModel error);
}
