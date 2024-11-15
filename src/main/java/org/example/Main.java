package org.example;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.adapter.presenter.HomePresenter;
import org.example.adapter.presenter.UpdateScreenPresenter;
import org.example.adapter.ui.CreateInvoiceScreen;
import org.example.adapter.ui.HomeUI;
import org.example.adapter.presenter.CreateScreenPresenter;
import org.example.adapter.ui.UpdateInvoiceScreen;
import org.example.domain.boundaries.in.UpdateHomeScreenInputBoundary;
import org.example.domain.boundaries.out.GetTotalInvoicesOfCustomerTypeOutputBoundary;
import org.example.domain.boundaries.out.OpenUpdateScreenOutputBoundary;
import org.example.domain.usecases.*;

public class Main {
    public static void main(String[] args) {
        // repository
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        
        // create invoice screen
        CreateInvoiceScreen createInvoiceScreen = new CreateInvoiceScreen();
        CreateScreenPresenter createScreenPresenter = new CreateScreenPresenter(createInvoiceScreen, null);
        OpenCreateScreenUseCase openCreateScreenUseCase = new OpenCreateScreenUseCase(createScreenPresenter);

        CreateInvoiceUseCase createInvoiceUseCase = new CreateInvoiceUseCase(createScreenPresenter, vietnameseRepository, foreignRepository);
        createScreenPresenter = new CreateScreenPresenter(createInvoiceScreen, createInvoiceUseCase);

        
        // update invoice screen
        UpdateInvoiceScreen updateInvoiceScreen = new UpdateInvoiceScreen();
        UpdateScreenPresenter updateScreenPresenter = new UpdateScreenPresenter(updateInvoiceScreen, null, null);
        GetInvoiceByIdUseCase getInvoiceByIdUseCase = new GetInvoiceByIdUseCase(updateScreenPresenter, vietnameseRepository, foreignRepository);
        UpdateInvoiceUseCase updateInvoiceUseCase = new UpdateInvoiceUseCase(updateScreenPresenter, vietnameseRepository, foreignRepository);
        updateScreenPresenter = new UpdateScreenPresenter(updateInvoiceScreen, getInvoiceByIdUseCase, updateInvoiceUseCase);
        
        
        // home screen
        HomeUI homeUI = new HomeUI();
        HomePresenter homePresenter = new HomePresenter(
                homeUI, 
                null, 
                null,
                null,
                null,
                null,
                null,
                null
        );
        
        UpdateHomeScreenUseCase updateHomeScreenInputBoundary = new UpdateHomeScreenUseCase(homePresenter);
        updateScreenPresenter.setUpdateHomeScreenInputBoundary(updateHomeScreenInputBoundary);
        createScreenPresenter.setUpdateHomeScreenInputBoundary(updateHomeScreenInputBoundary);
        createInvoiceScreen.setPresenter(createScreenPresenter);
        updateInvoiceScreen.setPresenter(updateScreenPresenter);
        
        
        GetTotalInvoicesUseCase getTotalInvoicesUseCase = new GetTotalInvoicesUseCase(homePresenter, vietnameseRepository, foreignRepository);
        GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase
                = new GetTotalInvoicesOfCustomerTypeUseCase(homePresenter, vietnameseRepository, foreignRepository);
        GetListInvoicesUseCase getListInvoicesUseCase = new GetListInvoicesUseCase(homePresenter, vietnameseRepository, foreignRepository); 
        DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase = new DeleteInvoiceByIdUseCase(homePresenter, vietnameseRepository, foreignRepository);
        OpenUpdateScreenUseCase openUpdateScreenUseCase = new OpenUpdateScreenUseCase(updateScreenPresenter); 
        SearchInvoiceByNameUseCase searchInvoiceByNameUseCase = new SearchInvoiceByNameUseCase(homePresenter, vietnameseRepository, foreignRepository);
        homePresenter = new HomePresenter(
                homeUI, 
                getTotalInvoicesUseCase, 
                getTotalInvoicesOfCustomerTypeUseCase,
                getListInvoicesUseCase,
                deleteInvoiceByIdUseCase,
                openUpdateScreenUseCase,
                openCreateScreenUseCase,
                searchInvoiceByNameUseCase
        );
        homeUI.setPresenter(homePresenter);
        homeUI.init();
    }
}