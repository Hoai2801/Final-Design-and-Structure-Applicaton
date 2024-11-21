package org.example.adapter.presenter;

import org.example.adapter.ui.UpdateInvoiceScreen;
import org.example.domain.boundaries.out.*;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public class UpdateScreenPresenter extends Presenter implements 
        GetInvoiceByIdOutputBoundary, 
        UpdateInvoiceOutputBoundary,
        OpenUpdateScreenOutputBoundary,
        GetCustomerTypeOutputBoundary,
        GetNationalityOutputBoundary
{
    private final UpdateInvoiceScreen view;
    
    public UpdateScreenPresenter(UpdateInvoiceScreen updateInvoiceScreen) {
        this.view = updateInvoiceScreen;
    }
    

    @Override
    public void getInvoiceById(InvoiceDTO invoice) {
        view.fetchInvoice(invoice);
    }


    @Override
    public void update(ResponseModel res) {
        System.out.println("update");
        notify(res);
    }

    @Override
    public void openUpdateScreen(int customerId, String type) {
        view.open(customerId, type);
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
