package org.example.adapter.presenter;

import org.example.adapter.ui.CreateInvoiceScreen;
import org.example.adapter.ui.Screen;
import org.example.domain.boundaries.in.*;
import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.GetCustomerTypeOutputBoundary;
import org.example.domain.boundaries.out.GetNationalityOutputBoundary;
import org.example.domain.boundaries.out.OpenCreateScreenOutputBoundary;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CreateScreenPresenter extends Presenter implements OpenCreateScreenOutputBoundary, CreateInvoiceOutputBoundary, GetCustomerTypeOutputBoundary, GetNationalityOutputBoundary {
    private final CreateInvoiceScreen view;
    
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
    public void onCreate(ResponseModel message) {
        isSuccess = true;
        showNotification(message);
        view.clearForm();
    }
    
    public void showNotification(ResponseModel message) {
        notify(message);
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
