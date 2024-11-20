package org.example.adapter.presenter;

import org.example.adapter.ui.HomeUI;
import org.example.domain.boundaries.out.*;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public class HomePresenter implements 
        GetTotalInvoiceOutputBoundary, 
        GetTotalInvoicesOfCustomerTypeOutputBoundary, 
        GetListInvoicesOutputBoundary, 
        DeleteInvoiceByIdOutputBoundary,
        UpdateHomeScreenOutputBoundary,
        SearchInvoiceByNameOutputBoundary
{
    private final HomeUI homeUI;
    
    public HomePresenter(
            HomeUI homeUI
    ) {
        this.homeUI = homeUI;
    }

    @Override
    public void showTotalInvoices(int i) {
        homeUI.showTotalInvoices(i);
    }

    @Override
    public void showTotalInvoicesOfVietnamCustomer(int i) {
        homeUI.showTotalInvoicesVietnamCustomer(i);
    }

    @Override
    public void showTotalInvoicesOfForeignCustomer(int i) {
        homeUI.showTotalInvoicesForeignCustomer(i);
    }

    @Override
    public void getListInvoices(List<InvoiceDTO> invoices) {
        homeUI.showListInvoices(invoices);
    }

    @Override
    public void onDeleteSuccess(ResponseModel responseModel) {
        homeUI.showNotification(responseModel);
    }

    @Override
    public void onDeleteFail(ResponseModel responseModel) {
        homeUI.showNotification(responseModel);
    }

    @Override
    public void updateHomeScreen() {
        homeUI.updateHomeScreen();
    }

    @Override
    public void showSearchInvoiceByName(List<InvoiceDTO> invoices) {
        homeUI.showListInvoices(invoices);
    }
}
