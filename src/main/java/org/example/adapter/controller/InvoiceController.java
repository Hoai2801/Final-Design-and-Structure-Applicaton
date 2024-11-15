package org.example.adapter.controller;

import org.example.domain.entities.models.RequestModel;
import org.example.domain.usecases.CreateInvoiceUseCase;
import org.example.domain.usecases.OpenCreateScreenUseCase;

public class InvoiceController {
    
//    private final InputInvoiceBoundary inputInvoiceBoundary;
//    private final CommandProcessor commandProcessor = CommandProcessor.makeCommandProcessor();
//    
//    public InvoiceController(InputInvoiceBoundary inputInvoiceBoundary) {
//        this.inputInvoiceBoundary = inputInvoiceBoundary;
//    }
//    
//    public void createInvoice(RequestModel req) {
//        commandProcessor.execute(new CreateCommand(inputInvoiceBoundary, req));
//    }
//    
//    public void updateInvoice(RequestModel req) {
//        commandProcessor.execute(new UpdateCommand(inputInvoiceBoundary, req));
//    }
//
//    public void deleteInvoice(RequestModel req) {
//        commandProcessor.execute(new DeleteCommand(inputInvoiceBoundary, req));
//    }
//    
//    public void getTotalAmountOfInvoice(int type) {
//        commandProcessor.execute(new CountInvoiceCommand(inputInvoiceBoundary, type));
//    }
//    
//    public void getTotalAmountOfInvoiceByMonth(int month) {
//        commandProcessor.execute(new CountInvoiceByMonthCommand(inputInvoiceBoundary, month));
//    }
//
//    public void generateReport() {
//        commandProcessor.execute(new GetReportCommand(inputInvoiceBoundary));
//    }
    private OpenCreateScreenUseCase openCreateScreenUseCase;
    private CreateInvoiceUseCase createInvoiceUseCase; 
    private static InvoiceController instance;

    public InvoiceController(OpenCreateScreenUseCase openCreateScreenUseCase, CreateInvoiceUseCase createInvoiceUseCase) {
        this.createInvoiceUseCase = createInvoiceUseCase;
        this.openCreateScreenUseCase = openCreateScreenUseCase;
    }
    
    public InvoiceController() {
    }
    
    public static void setInstance(InvoiceController invoiceController) {
        instance = invoiceController;
    }

    public static InvoiceController getInstance() {
        return instance;
    }

    public void openCreateInvoiceScreen() {
        openCreateScreenUseCase.execute();
    }


    public void createInvoice(RequestModel requestModel) {
        createInvoiceUseCase.createInvoice(requestModel);
//        showNotification();
    }
    
//    public void showNotification() {
//        NotificationPresenter presenter = NotificationPresenter.getInstance();
//        if (presenter.getNotifyResponse().isSuccess()) {
//            JOptionPane.showMessageDialog(null, presenter.getNotifyResponse().getMessage(), "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, presenter.getNotifyResponse().getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
}
