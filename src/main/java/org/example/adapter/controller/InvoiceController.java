package org.example.adapter.controller;

import org.example.adapter.command.*;
import org.example.domain.boundaries.in.InputInvoiceBoundary;
import org.example.domain.entities.models.RequestModel;

public class InvoiceController {
    
    private final InputInvoiceBoundary inputInvoiceBoundary;
    private final CommandProcessor commandProcessor = CommandProcessor.makeCommandProcessor();
    
    public InvoiceController(InputInvoiceBoundary inputInvoiceBoundary) {
        this.inputInvoiceBoundary = inputInvoiceBoundary;
    }
    
    public void createInvoice(RequestModel req) {
        commandProcessor.execute(new CreateCommand(inputInvoiceBoundary, req));
    }
    
    public void updateInvoice(RequestModel req) {
        commandProcessor.execute(new UpdateCommand(inputInvoiceBoundary, req));
    }

    public void deleteInvoice(RequestModel req) {
        commandProcessor.execute(new DeleteCommand(inputInvoiceBoundary, req));
    }
    
    public void getTotalAmountOfInvoice(int type) {
        commandProcessor.execute(new CountInvoiceCommand(inputInvoiceBoundary, type));
    }

    public void generateReport() {
        commandProcessor.execute(new GetReportCommand(inputInvoiceBoundary));
    }
    
    public void getAnalyst() {
        commandProcessor.execute(new GetAnalystCommand(inputInvoiceBoundary));
    }
}
