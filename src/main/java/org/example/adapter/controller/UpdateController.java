package org.example.adapter.controller;

import org.example.domain.boundaries.in.*;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.util.Valid;

import javax.swing.*;

public class UpdateController {
    private final GetInvoiceByIdInputBoundary getInvoiceByIdUseCase;
    private final UpdateInvoiceInputBoundary updateInvoiceUseCase;
    private final GetCustomerTypeInputBoundary getCustomerTypeInputBoundary;
    private final GetNationalityInputBoundary getNationalityInputBoundary;
    private UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary; 
    
    public UpdateController(
            GetInvoiceByIdInputBoundary getInvoiceByIdUseCase,
            UpdateInvoiceInputBoundary updateInvoiceUseCase,            
            GetCustomerTypeInputBoundary getCustomerTypeInputBoundary,
            GetNationalityInputBoundary getNationalityInputBoundary
    ) {
        this.getInvoiceByIdUseCase = getInvoiceByIdUseCase;
        this.updateInvoiceUseCase = updateInvoiceUseCase;
        this.getCustomerTypeInputBoundary = getCustomerTypeInputBoundary;
        this.getNationalityInputBoundary = getNationalityInputBoundary;
    }
    
    public void setUpdateHomeScreenInputBoundary(UpdateHomeScreenInputBoundary updateHomeScreenInputBoundary) {
        this.updateHomeScreenInputBoundary = updateHomeScreenInputBoundary;
    }

    public void loadDateForView(int invoiceId, String type) {
        getCustomerType();
        getNationality();
        getInvoiceById(invoiceId, type);
    }

    public void getInvoiceById(int invoiceId, String type) {
        getInvoiceByIdUseCase.getInvoiceById(invoiceId, type);
    }

    public void updateInvoice(RequestModel requestModel) {
        try {
            updateInvoiceUseCase.updateInvoice(requestModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fail", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateHomeScreenInputBoundary.execute();
    }

    public void updateHomeScreen() {
        updateHomeScreenInputBoundary.execute();
    }

    public void getCustomerType() {
        getCustomerTypeInputBoundary.execute();
    }

    public void getNationality() {
        getNationalityInputBoundary.execute();
    }
}