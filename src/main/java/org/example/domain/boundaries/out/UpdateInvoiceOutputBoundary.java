package org.example.domain.boundaries.out;

import org.example.domain.entities.models.ResponseModel;

public interface UpdateInvoiceOutputBoundary {
    void updateSuccess(ResponseModel res);
    void updateFail(ResponseModel res);
}
