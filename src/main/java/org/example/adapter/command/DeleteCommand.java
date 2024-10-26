package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;
import org.example.domain.entities.models.RequestModel;

public class DeleteCommand extends Command {
    private final RequestModel invoice;
    public DeleteCommand(InputInvoiceBoundary invoiceService, RequestModel invoice) {
        super(invoiceService);
        this.invoice = invoice;
    }
    @Override
    public void execute() {
        invoiceService.deleteInvoice(invoice);
    }
}
