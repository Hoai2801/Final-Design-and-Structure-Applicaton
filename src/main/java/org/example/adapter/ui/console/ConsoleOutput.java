package org.example.adapter.ui.console;

import org.example.adapter.observer.Observer;
import org.example.adapter.ui.model.InvoicesModel;
import org.example.domain.entities.models.ListInvoiceResponseModel;
import org.example.domain.entities.models.ResponseModel;

import java.util.List;

public class ConsoleOutput implements Observer {
    private ConsoleInput consoleInput;

    public ConsoleOutput() {
    }

    public void showMessage(ResponseModel message) {
        System.out.println(message.getMessage());
        consoleInput.start();
    }

    public void showReport(List<InvoicesModel> invoices) {
        for (InvoicesModel invoice : invoices) {
            System.out.println(invoice.toString());
        }
        consoleInput.start();
    }

    public void showError(ResponseModel error) {
        System.out.println("Error: " + error.getMessage());
        consoleInput.start();

    }

    public void setConsoleInput(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

    @Override
    public void updateMessage(ResponseModel responseModel) {
        showMessage(responseModel);
    }

    @Override
    public void updateError(ResponseModel error) {
        showError(error);
    }

    @Override
    public void generateReport(ListInvoiceResponseModel invoices) {
        
    }
}
