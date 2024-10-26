package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;

public abstract class Command {
    protected InputInvoiceBoundary invoiceService;

    public Command(InputInvoiceBoundary invoiceService) {
        this.invoiceService = invoiceService;
    }

    public abstract void execute();
}
