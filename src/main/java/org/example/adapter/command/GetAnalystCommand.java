package org.example.adapter.command;

import org.example.domain.boundaries.in.InputInvoiceBoundary;
import org.example.domain.entities.models.RequestModel;

public class GetAnalystCommand extends Command{
    public GetAnalystCommand(InputInvoiceBoundary invoiceService) {
        super(invoiceService);
    }
    @Override
    public void execute() {
//        invoiceService.getTotalAmountOfInvoice(1);
//        invoiceService.getTotalAmountOfInvoice(2);
        invoiceService.getAnalyst();
    }
}
