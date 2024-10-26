package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;

public class CountInvoiceCommand extends Command {
    private final int type;
    public CountInvoiceCommand(InputInvoiceBoundary invoiceService, int type) {
        super(invoiceService);
        this.type = type;
    }
    @Override
    public void execute() {
        invoiceService.getTotalAmountOfInvoice(type);
    }
}
