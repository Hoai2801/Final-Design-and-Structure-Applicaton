package org.example.adapter.presenter;

import org.example.adapter.ui.CreateInvoiceScreen;
import org.example.domain.boundaries.in.CreateInvoiceInputBoundary;
import org.example.domain.boundaries.in.OpenStateManageCreateScreenInputBoundary;
import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.OpenCreateScreenOutputBoundary;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;

public class CreateScreenPresenter implements OpenCreateScreenOutputBoundary, CreateInvoiceOutputBoundary {
    private final CreateInvoiceScreen view;
    private final CreateInvoiceInputBoundary createInvoiceUseCase;
    private UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary; 
    private boolean isOpen = false;
    private boolean isSuccess = false;
    
    public CreateScreenPresenter(
            CreateInvoiceScreen createInvoiceScreen, 
            CreateInvoiceInputBoundary createInvoiceUseCase
    ) {
        this.view = createInvoiceScreen;
        this.createInvoiceUseCase = createInvoiceUseCase;
    }
    
    @Override
    public void openCreateScreen() {
        view.open();
        isOpen = true;
    }

    public void setUpdateHomeScreenInputBoundary(UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary) {
        this.updateHomeScreenInputBoundary = updateHomeScreenInputBoundary;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void onSuccess(ResponseModel message) {
        isSuccess = true;
        showNotification(message);
        view.clearForm();
    }

    @Override
    public void onError(ResponseModel error) {
        isSuccess = false;
        showNotification(error);
    }
    
    public boolean isSuccess() {
        return isSuccess;
    }
    
    public void showNotification(ResponseModel message) {
        view.showNotification(message);
    }

    public void createInvoice(RequestModel requestModel) {
        createInvoiceUseCase.createInvoice(requestModel);
    }

    public void updateHomeScreen() {
        updateHomeScreenInputBoundary.execute();
    }
}
