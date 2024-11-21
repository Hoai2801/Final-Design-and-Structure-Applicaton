package org.example.domain.usecases;

import org.example.domain.boundaries.in.GetTotalInvoiceOfForeignInputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.GetTotalInvoiceOfForeignOutputBoundary;

public class GetTotalInvoiceOfForeignUseCase implements GetTotalInvoiceOfForeignInputBoundary {
    private final GetTotalInvoiceOfForeignOutputBoundary outputBoundary;
    private final ForeignInvoiceRepository foreignRepository;
    
    public GetTotalInvoiceOfForeignUseCase(GetTotalInvoiceOfForeignOutputBoundary outputBoundary, ForeignInvoiceRepository foreignRepository) {
        this.outputBoundary = outputBoundary;
        this.foreignRepository = foreignRepository;
    }
    
    @Override
    public void execute() {
        int foreignTotal = foreignRepository.getTotalAmountOfInvoice();
        outputBoundary.getTotalInvoiceOfForeign(foreignTotal);
    }
}
