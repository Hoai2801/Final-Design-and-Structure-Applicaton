package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;

public class CountInvoiceByMonthCommand extends Command {
    private int month;
    public CountInvoiceByMonthCommand(InputInvoiceBoundary invoiceService, int month) {
        super(invoiceService);
        this.month = month;
    }
    @Override
    public void execute() {
        invoiceService.getTotalAmountOfInvoiceByMonth(month);
    }
}
