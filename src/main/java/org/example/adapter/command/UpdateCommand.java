package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;
import org.example.domain.entities.Invoice;
import org.example.domain.entities.models.RequestModel;

public class UpdateCommand extends Command {
    private final RequestModel invoice;
    public UpdateCommand(InputInvoiceBoundary invoiceService, RequestModel invoice) {
        super(invoiceService);
        this.invoice = invoice;
    } 
    @Override
    public void execute() {
        invoiceService.updateInvoice(invoice);
    }
}
