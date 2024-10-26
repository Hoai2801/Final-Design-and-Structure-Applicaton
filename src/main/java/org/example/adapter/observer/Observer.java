package org.example.adapter.observer;

import org.example.adapter.ui.model.InvoicesModel;
import org.example.domain.entities.models.ListInvoiceResponseModel;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public interface Observer {
    public void updateMessage(ResponseModel responseModel);

    public void updateError(ResponseModel error);

    public void generateReport(ListInvoiceResponseModel invoices);
}
