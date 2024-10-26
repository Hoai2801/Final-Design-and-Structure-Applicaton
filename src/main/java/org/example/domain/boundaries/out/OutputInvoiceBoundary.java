package org.example.domain.boundaries.out;

import org.example.domain.entities.models.ListInvoiceResponseModel;
import org.example.domain.entities.models.ResponseModel;

public interface OutputInvoiceBoundary {
    void showMessage(ResponseModel message);

    void showReport(ListInvoiceResponseModel invoices);

    void showError(ResponseModel error);
}
