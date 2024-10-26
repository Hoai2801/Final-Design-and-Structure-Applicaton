package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;

public class GetReportCommand extends Command {

    public GetReportCommand(InputInvoiceBoundary invoiceService) {
        super(invoiceService);
    }
    @Override
    public void execute() {
        invoiceService.getInvoices();
    }
}
