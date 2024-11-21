package org.example.adapter.presenter;

import org.example.adapter.ui.HomeUI;
import org.example.domain.boundaries.out.*;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public class HomePresenter extends Presenter implements 
        GetTotalInvoiceOutputBoundary, 
        GetTotalInvoiceOfVietnameseOutputBoundary,
        GetTotalInvoiceOfForeignOutputBoundary,
        GetListInvoicesOutputBoundary, 
        DeleteInvoiceByIdOutputBoundary,
        UpdateHomeScreenOutputBoundary,
        SearchInvoiceByNameOutputBoundary,
        InitHomeOutputBoundary
{
    private final HomeUI homeUI;
    
    private int totalInvoices = 0;
    private int totalInvoicesOfVietnamCustomer = 0;
    private int totalInvoicesOfForeignCustomer = 0;
    private List<InvoiceDTO> invoices = null;
    
    public HomePresenter(
            HomeUI homeUI
    ) {
        this.homeUI = homeUI;
    }

    @Override
    public void showTotalInvoices(int i) {
        totalInvoices = i;
    }

    @Override
    public void getListInvoices(List<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }

    @Override
    public void updateHomeScreen() {
        homeUI.updateHomeScreen();
    }

    @Override
    public void showSearchInvoiceByName(List<InvoiceDTO> invoices) {
        homeUI.showListInvoices(invoices);
    }

    @Override
    public void initHome() {
        homeUI.showTotalInvoices(totalInvoices);
        homeUI.showTotalInvoicesVietnamCustomer(totalInvoicesOfVietnamCustomer);
        homeUI.showTotalInvoicesForeignCustomer(totalInvoicesOfForeignCustomer);
        homeUI.showListInvoices(invoices);
    }

    @Override
    public void deleteInvoice(ResponseModel responseModel) {
        notify(responseModel);
    }

    @Override
    public void getTotalInvoiceOfForeign(int i) {
        totalInvoicesOfForeignCustomer = i;
    }

    @Override
    public void getTotalInvoiceOfVietnamese(int i) {
        totalInvoicesOfVietnamCustomer = i;
    }
}
