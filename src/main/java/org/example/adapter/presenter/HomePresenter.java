package org.example.adapter.presenter;

import org.example.adapter.ui.HomeUI;
import org.example.domain.boundaries.in.GetTotalInvoiceInputBoundary;
import org.example.domain.boundaries.in.OpenChartScreenInputBoundary;
import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.boundaries.out.*;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.usecases.*;

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
    private final GetTotalInvoiceInputBoundary getTotalInvoiceUseCase;
    private final GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase;
    private final GetListInvoicesUseCase getListInvoicesUseCase;
    private final DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase;
    private final OpenUpdateScreenUseCase openUpdateScreenUseCase;
    private final OpenCreateScreenUseCase openCreateScreenUseCase;
    private final SearchInvoiceByNameUseCase searchInvoiceByNameUseCase;
    OpenChartScreenInputBoundary openChartScreenInputBoundary;
    
    public HomePresenter(
            HomeUI homeUI,
            GetTotalInvoiceInputBoundary getTotalInvoiceUseCase,
            GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase,
            GetListInvoicesUseCase getListInvoicesUseCase,
            DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase,
            OpenUpdateScreenUseCase openUpdateScreenUseCase,
            OpenCreateScreenUseCase openCreateScreenUseCase,
            SearchInvoiceByNameUseCase searchInvoiceByNameUseCase,
            OpenChartScreenInputBoundary openChartScreenInputBoundary
    ) {
        this.homeUI = homeUI;
        this.getTotalInvoiceUseCase = getTotalInvoiceUseCase;
        this.getTotalInvoicesOfCustomerTypeUseCase = getTotalInvoicesOfCustomerTypeUseCase;
        this.getListInvoicesUseCase = getListInvoicesUseCase;
        this.deleteInvoiceByIdUseCase = deleteInvoiceByIdUseCase;
        this.openCreateScreenUseCase = openCreateScreenUseCase;
        this.openUpdateScreenUseCase = openUpdateScreenUseCase;
        this.searchInvoiceByNameUseCase = searchInvoiceByNameUseCase;
        this.openChartScreenInputBoundary = openChartScreenInputBoundary;
    }
    
    public void getTotalInvoices() {
        getTotalInvoiceUseCase.execute();
    }
    
    public void getTotalInvoicesOfCustomerType() {
        getTotalInvoicesOfCustomerTypeUseCase.execute();
    }
    
    public void getListInvoices() {
        getListInvoicesUseCase.execute();
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

    public void deleteInvoice(int customerId, String type) {
        deleteInvoiceByIdUseCase.deleteInvoiceById(customerId, type);
    }

    @Override
    public void updateHomeScreen() {
        System.out.println("home presenter");
        homeUI.updateHomeScreen();
    }

    public void openCreateScreen() {
        openCreateScreenUseCase.execute();
    }

    public void openUpdateScreen(int customerId, String type) {
        openUpdateScreenUseCase.execute(customerId, type);
        
    }

    @Override
    public void showSearchInvoiceByName(List<InvoiceDTO> invoices) {
        homeUI.showListInvoices(invoices);
    }

    public void searchInvoice(String name) {
        searchInvoiceByNameUseCase.execute(name);
    }

    public void openChartScreen() {
        openChartScreenInputBoundary.execute();
    }
}
