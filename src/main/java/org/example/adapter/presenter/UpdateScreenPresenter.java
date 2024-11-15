package org.example.adapter.presenter;

import org.example.adapter.ui.UpdateInvoiceScreen;
import org.example.domain.boundaries.in.GetInvoiceByIdInputBoundary;
import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.boundaries.in.UpdateInvoiceInputBoundary;
import org.example.domain.boundaries.out.GetInvoiceByIdOutputBoundary;
import org.example.domain.boundaries.out.OpenUpdateScreenOutputBoundary;
import org.example.domain.boundaries.out.UpdateInvoiceOutputBoundary;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

public class UpdateScreenPresenter implements 
        GetInvoiceByIdOutputBoundary, 
        UpdateInvoiceOutputBoundary,
        OpenUpdateScreenOutputBoundary
{
    private final UpdateInvoiceScreen view;
    private final GetInvoiceByIdInputBoundary getInvoiceByIdUseCase;
    private final UpdateInvoiceInputBoundary updateInvoiceUseCase;
    private UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary;
    
    public UpdateScreenPresenter(
            UpdateInvoiceScreen updateInvoiceScreen,
            GetInvoiceByIdInputBoundary getInvoiceByIdUseCase,
            UpdateInvoiceInputBoundary updateInvoiceUseCase
    ) {
        this.view = updateInvoiceScreen;
        this.getInvoiceByIdUseCase = getInvoiceByIdUseCase;
        this.updateInvoiceUseCase = updateInvoiceUseCase;
    }
    
    public void setUpdateHomeScreenInputBoundary(UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary) {
        this.updateHomeScreenInputBoundary = updateHomeScreenInputBoundary;
    }
    
    public void getInvoiceById(int invoiceId, String type) {
        getInvoiceByIdUseCase.getInvoiceById(invoiceId, type);
    }

    @Override
    public void getInvoiceById(InvoiceDTO invoice) {
        view.fetchInvoice(invoice);
    }

    public void updateInvoice(RequestModel requestModel) {
        updateInvoiceUseCase.updateInvoice(requestModel);
    }

    @Override
    public void updateSuccess(ResponseModel res) {
        view.showNotification(res);
        view.dispose();
    }
    
    public void updateHomeScreen() {
        updateHomeScreenInputBoundary.execute();
    }

    @Override
    public void updateFail(ResponseModel res) {
        view.showNotification(res);
    }

    @Override
    public void openUpdateScreen() {
        view.open();
    }

    @Override
    public void openUpdateScreen(int customerId, String type) {
        view.open();
        getInvoiceById(customerId, type);
    }
}
