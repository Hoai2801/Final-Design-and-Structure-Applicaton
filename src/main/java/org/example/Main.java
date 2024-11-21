package org.example;

import org.example.adapter.controller.ChartController;
import org.example.adapter.controller.CreateController;
import org.example.adapter.controller.HomeController;
import org.example.adapter.controller.UpdateController;
import org.example.adapter.database.CustomerTypeRepositoryImpl;
import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.NationalityRepositoryImpl;
import org.example.adapter.database.VietnameseRepository;
import org.example.adapter.presenter.ChartPresenter;
import org.example.adapter.presenter.HomePresenter;
import org.example.adapter.presenter.UpdateScreenPresenter;
import org.example.adapter.ui.ChartScreen;
import org.example.adapter.ui.CreateInvoiceScreen;
import org.example.adapter.ui.HomeUI;
import org.example.adapter.presenter.CreateScreenPresenter;
import org.example.adapter.ui.UpdateInvoiceScreen;
import org.example.domain.boundaries.in.GetCustomerTypeInputBoundary;
import org.example.domain.boundaries.in.GetNationalityInputBoundary;
import org.example.domain.boundaries.out.NationalityRepository;
import org.example.domain.usecases.*;

public class Main {
    public static void main(String[] args) {
        // repository
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        CustomerTypeRepositoryImpl customerTypeRepository = new CustomerTypeRepositoryImpl();
        NationalityRepository nationalityRepository = new NationalityRepositoryImpl();
        
        // create invoice screen
        CreateInvoiceScreen createInvoiceScreen = new CreateInvoiceScreen();
        CreateScreenPresenter createScreenPresenter = new CreateScreenPresenter(createInvoiceScreen);
        OpenCreateScreenUseCase openCreateScreenUseCase = new OpenCreateScreenUseCase(createScreenPresenter);
        GetCustomerTypeInputBoundary getCustomerTypeUseCase = new GetCustomerTypeUseCase(createScreenPresenter, customerTypeRepository);
        GetNationalityInputBoundary getNationalityUseCase = new GetNationalityUseCase(createScreenPresenter, nationalityRepository);
        CreateInvoiceUseCase createInvoiceUseCase = new CreateInvoiceUseCase(createScreenPresenter, vietnameseRepository, foreignRepository);
        CreateController createController = new CreateController(createInvoiceUseCase, getCustomerTypeUseCase, getNationalityUseCase);
        createInvoiceScreen.setController(createController); 

        
        // update invoice screen
        UpdateInvoiceScreen updateInvoiceScreen = new UpdateInvoiceScreen();
        UpdateScreenPresenter updateScreenPresenter = new UpdateScreenPresenter(updateInvoiceScreen);
        GetInvoiceByIdUseCase getInvoiceByIdUseCase = new GetInvoiceByIdUseCase(updateScreenPresenter, vietnameseRepository, foreignRepository);
        getCustomerTypeUseCase = new GetCustomerTypeUseCase(updateScreenPresenter, customerTypeRepository);
        getNationalityUseCase = new GetNationalityUseCase(updateScreenPresenter, nationalityRepository);
        UpdateInvoiceUseCase updateInvoiceUseCase = new UpdateInvoiceUseCase(updateScreenPresenter, vietnameseRepository, foreignRepository);
        var updateController = new UpdateController(getInvoiceByIdUseCase, updateInvoiceUseCase, getCustomerTypeUseCase, getNationalityUseCase);    
        updateInvoiceScreen.setController(updateController);
        
        // chart screen
        ChartScreen chartScreen = new ChartScreen();
        ChartPresenter chartPresenter = new ChartPresenter(chartScreen);
        GetAnalystUseCase getAnalystUseCase = new GetAnalystUseCase(chartPresenter, vietnameseRepository, foreignRepository);
        ChartController chartController = new ChartController(getAnalystUseCase);
        chartScreen.setController(chartController);
        
        // home screen
        HomeUI homeUI = new HomeUI();
        
        HomePresenter homePresenter = new HomePresenter(homeUI);
        
        homePresenter.addScreen(homeUI);
        createScreenPresenter.addScreen(homeUI);
        createScreenPresenter.addScreen(createInvoiceScreen);
        updateScreenPresenter.addScreen(homeUI);
        updateScreenPresenter.addScreen(updateInvoiceScreen);
        
        UpdateHomeScreenUseCase updateHomeScreenUseCase = new UpdateHomeScreenUseCase(homePresenter);
        createController.setUpdateHomeScreenUseCase(updateHomeScreenUseCase);
        updateController.setUpdateHomeScreenInputBoundary(updateHomeScreenUseCase);
        
        // home 
        GetTotalInvoicesUseCase getTotalInvoicesUseCase = new GetTotalInvoicesUseCase(homePresenter, vietnameseRepository, foreignRepository);
        GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase
                = new GetTotalInvoicesOfCustomerTypeUseCase(homePresenter, vietnameseRepository, foreignRepository);
        GetListInvoicesUseCase getListInvoicesUseCase = new GetListInvoicesUseCase(homePresenter, vietnameseRepository, foreignRepository); 
        DeleteInvoiceByIdUseCase deleteInvoiceByIdUseCase = new DeleteInvoiceByIdUseCase(homePresenter, vietnameseRepository, foreignRepository);
        OpenUpdateScreenUseCase openUpdateScreenUseCase = new OpenUpdateScreenUseCase(updateScreenPresenter); 
        SearchInvoiceByNameUseCase searchInvoiceByNameUseCase = new SearchInvoiceByNameUseCase(homePresenter, vietnameseRepository, foreignRepository);
        OpenChartScreenUseCase openChartScreenUseCase = new OpenChartScreenUseCase(chartScreen);
        InitHomeUseCase initHomeUseCase = new InitHomeUseCase(homePresenter, getListInvoicesUseCase, getTotalInvoicesOfCustomerTypeUseCase, getTotalInvoicesUseCase);
        HomeController homeController = new HomeController(
//                getTotalInvoicesUseCase,
//                getTotalInvoicesOfCustomerTypeUseCase,
//                getListInvoicesUseCase,
                initHomeUseCase,
                deleteInvoiceByIdUseCase,
                openUpdateScreenUseCase,
                openCreateScreenUseCase,
                searchInvoiceByNameUseCase,
                openChartScreenUseCase
        );
        homeUI.setHomeController(homeController);
        homeUI.init();
    }
}