package org.example.domain.usecases;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.in.GetTotalInvoicesOfCustomerTypeInputBoundary;
import org.example.domain.boundaries.out.GetTotalInvoicesOfCustomerTypeOutputBoundary;

public class GetTotalInvoicesOfCustomerTypeUseCase implements GetTotalInvoicesOfCustomerTypeInputBoundary {
    private final GetTotalInvoicesOfCustomerTypeOutputBoundary outputBoundary; 
    private final VietnameseRepository vietnameseRepository;
    private final ForeignRepository foreignRepository; 
    
    public GetTotalInvoicesOfCustomerTypeUseCase(
            GetTotalInvoicesOfCustomerTypeOutputBoundary outputBoundary,
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
        
        outputBoundary.showTotalInvoicesOfVietnamCustomer(vietnameseTotal);
        outputBoundary.showTotalInvoicesOfForeignCustomer(foreignTotal);
    }
}
