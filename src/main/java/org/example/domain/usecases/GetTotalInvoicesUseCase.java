package org.example.domain.usecases;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.in.GetTotalInvoiceInputBoundary;
import org.example.domain.boundaries.out.GetTotalInvoiceOutputBoundary;

public class GetTotalInvoicesUseCase implements GetTotalInvoiceInputBoundary {
    private final GetTotalInvoiceOutputBoundary outputBoundary;
    private final VietnameseRepository vietnameseRepository;
    private final ForeignRepository foreignRepository; 
    public GetTotalInvoicesUseCase(
            GetTotalInvoiceOutputBoundary outputBoundary,
            VietnameseRepository vietnameseRepository,
            ForeignRepository foreignRepository
    ) {
        this.outputBoundary = outputBoundary;
        this.vietnameseRepository = vietnameseRepository;
        this.foreignRepository = foreignRepository;
    }
    
    @Override
    public void execute() {
        int vietnameseTotal = vietnameseRepository.getTotalAmountOfInvoice();
        int foreignTotal = foreignRepository.getTotalAmountOfInvoice();
        outputBoundary.showTotalInvoices(vietnameseTotal + foreignTotal);
    }
}
