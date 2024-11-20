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
    private UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary;
    
    private boolean isOpen = false;
    private boolean isSuccess = false;
    
    public CreateScreenPresenter(CreateInvoiceScreen createInvoiceScreen) {
        this.view = createInvoiceScreen;
    }
    
    @Override
    public void openCreateScreen() {
        view.open();
        isOpen = true;
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
    
    public void showNotification(ResponseModel message) {
        view.showNotification(message);
    }

    @Override
    public void setCustomerType(List<String> customerTypes) {
        view.setCustomerType(customerTypes);
    }

    @Override
    public void setNationality(List<String> nationality) {
        view.setNationality(nationality);
    }
    
}
