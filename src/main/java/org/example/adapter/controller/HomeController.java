package org.example.adapter.controller;

import org.example.domain.boundaries.in.GetTotalInvoiceInputBoundary;
import org.example.domain.boundaries.in.InitHomeInputBoundary;
import org.example.domain.boundaries.in.OpenChartScreenInputBoundary;
import org.example.domain.boundaries.out.InitHomeOutputBoundary;
import org.example.domain.usecases.*;

public class HomeController {
//    private final GetTotalInvoiceInputBoundary getTotalInvoiceUseCase;
//    private final GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase;
//    private final GetListInvoicesUseCase getListInvoicesUseCase;
    private final InitHomeInputBoundary initHomeInputBoundary;
    private final DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase;
    private final OpenUpdateScreenUseCase openUpdateScreenUseCase;
    private final OpenCreateScreenUseCase openCreateScreenUseCase;
    private final SearchInvoiceByNameUseCase searchInvoiceByNameUseCase;
    OpenChartScreenInputBoundary openChartScreenInputBoundary;
    
    public HomeController(
//            GetTotalInvoiceInputBoundary getTotalInvoiceUseCase,
//            GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase,
//            GetListInvoicesUseCase getListInvoicesUseCase,
            InitHomeInputBoundary initHomeInputBoundary,
            DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase,
            OpenUpdateScreenUseCase openUpdateScreenUseCase,
            OpenCreateScreenUseCase openCreateScreenUseCase,
            SearchInvoiceByNameUseCase searchInvoiceByNameUseCase,
            OpenChartScreenInputBoundary openChartScreenInputBoundary
    ) {
//        this.getTotalInvoiceUseCase = getTotalInvoiceUseCase;
//        this.getTotalInvoicesOfCustomerTypeUseCase = getTotalInvoicesOfCustomerTypeUseCase;
//        this.getListInvoicesUseCase = getListInvoicesUseCase;
        this.initHomeInputBoundary = initHomeInputBoundary;
        this.deleteInvoiceByIdUseCase = deleteInvoiceByIdUseCase;
        this.openCreateScreenUseCase = openCreateScreenUseCase;
        this.openUpdateScreenUseCase = openUpdateScreenUseCase;
        this.searchInvoiceByNameUseCase = searchInvoiceByNameUseCase;
        this.openChartScreenInputBoundary = openChartScreenInputBoundary;
    }

//    public void getTotalInvoices() {
//        getTotalInvoiceUseCase.execute();
//    }
//
//    public void getTotalInvoicesOfCustomerType() {
//        getTotalInvoicesOfCustomerTypeUseCase.execute();
//    }
//
//    public void getListInvoices() {
//        getListInvoicesUseCase.execute();
//    }
    
    public void initHomeScreen() {
        initHomeInputBoundary.execute();
    }

    public void deleteInvoice(int customerId, String type) {
        deleteInvoiceByIdUseCase.deleteInvoiceById(customerId, type);
    }

    public void openCreateScreen() {
        openCreateScreenUseCase.execute();
    }

    public void openUpdateScreen(int customerId, String type) {
        openUpdateScreenUseCase.execute(customerId, type);
    }

    public void searchInvoice(String name) {
        searchInvoiceByNameUseCase.execute(name);
    }

    public void openChartScreen() {
        openChartScreenInputBoundary.execute();
    }
}
