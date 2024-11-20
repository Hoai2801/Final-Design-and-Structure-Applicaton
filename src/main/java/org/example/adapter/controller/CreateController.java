package org.example.adapter.controller;

import org.example.domain.boundaries.in.CreateInvoiceInputBoundary;
import org.example.domain.boundaries.in.GetCustomerTypeInputBoundary;
import org.example.domain.boundaries.in.GetNationalityInputBoundary;
import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.util.Valid;

import javax.swing.*;

public class CreateController {
    private final CreateInvoiceInputBoundary createInvoiceUseCase;
    private final GetCustomerTypeInputBoundary getCustomerTypeUseCase;
    private final GetNationalityInputBoundary getNationalityUseCase;
    private UpdateHomeScreenInputBoundary updateHomeScreenUseCase;
    
    public CreateController(
            CreateInvoiceInputBoundary createInvoiceUseCase,
            GetCustomerTypeInputBoundary getCustomerTypeUseCase,
            GetNationalityInputBoundary getNationalityUseCase
    ) {
        this.createInvoiceUseCase = createInvoiceUseCase;
        this.getCustomerTypeUseCase = getCustomerTypeUseCase;
        this.getNationalityUseCase = getNationalityUseCase;
    }

    public void setUpdateHomeScreenUseCase(UpdateHomeScreenInputBoundary updateHomeScreenUseCase) {
        this.updateHomeScreenUseCase = updateHomeScreenUseCase;
    }

    public void createInvoice(RequestModel requestModel) {
        try {
            createInvoiceUseCase.createInvoice(requestModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fail", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateHomeScreen();
    }

    public void getCustomerType() {
        getCustomerTypeUseCase.execute();
    }

    public void getNationality() {
        getNationalityUseCase.execute();
    }
    
    public void updateHomeScreen() {
        updateHomeScreenUseCase.execute();
    }
}
