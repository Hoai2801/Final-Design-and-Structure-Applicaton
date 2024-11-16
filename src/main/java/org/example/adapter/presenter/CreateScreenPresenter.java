package org.example.adapter.presenter;

import org.example.adapter.ui.CreateInvoiceScreen;
import org.example.domain.boundaries.in.*;
import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.GetCustomerTypeOutputBoundary;
import org.example.domain.boundaries.out.GetNationalityOutputBoundary;
import org.example.domain.boundaries.out.OpenCreateScreenOutputBoundary;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.util.List;

public class CreateScreenPresenter implements OpenCreateScreenOutputBoundary, CreateInvoiceOutputBoundary, GetCustomerTypeOutputBoundary, GetNationalityOutputBoundary {
    private final CreateInvoiceScreen view;
    private final CreateInvoiceInputBoundary createInvoiceUseCase;
    private UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary;
    private final GetCustomerTypeInputBoundary getCustomerTypeUseCase;
    private final GetNationalityInputBoundary getNationalityUseCase;
    private boolean isOpen = false;
    private boolean isSuccess = false;
    
    public CreateScreenPresenter(
            CreateInvoiceScreen createInvoiceScreen, 
            CreateInvoiceInputBoundary createInvoiceUseCase,
            GetCustomerTypeInputBoundary getCustomerTypeUseCase,
            GetNationalityInputBoundary getNationalityUseCase
    ) {
        this.view = createInvoiceScreen;
        this.createInvoiceUseCase = createInvoiceUseCase;
        this.getCustomerTypeUseCase = getCustomerTypeUseCase;
        this.getNationalityUseCase = getNationalityUseCase;
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

    @Override
    public void setCustomerType(List<String> customerTypes) {
        view.setCustomerType(customerTypes);
    }

    public void getCustomerType() {
        getCustomerTypeUseCase.execute();
    }

    @Override
    public void setNationality(List<String> nationality) {
        view.setNationality(nationality);
    }

    public void getNationality() {
        getNationalityUseCase.execute();
    }
}
