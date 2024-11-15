package org.example.domain.boundaries.out;

import org.example.domain.entities.models.ResponseModel;

public interface CreateInvoiceOutputBoundary {
    void onSuccess(ResponseModel message);
    void onError(ResponseModel error);
}
