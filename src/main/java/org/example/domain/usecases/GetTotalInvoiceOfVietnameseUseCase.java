package org.example.domain.usecases;

import org.example.domain.boundaries.in.GetTotalInvoiceOfVietnameseInputBoundary;
import org.example.domain.boundaries.out.GetTotalInvoiceOfVietnameseOutputBoundary;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;

public class GetTotalInvoiceOfVietnameseUseCase implements GetTotalInvoiceOfVietnameseInputBoundary {
    private final GetTotalInvoiceOfVietnameseOutputBoundary outputBoundary;
    private final VietnameseInvoiceRepository vietnameseRepository;
    public GetTotalInvoiceOfVietnameseUseCase(GetTotalInvoiceOfVietnameseOutputBoundary outputBoundary, VietnameseInvoiceRepository vietnameseRepository) {
        this.outputBoundary = outputBoundary;
        this.vietnameseRepository = vietnameseRepository;
    }

    @Override
    public void execute() {
        int vietnameseTotal = vietnameseRepository.getTotalAmountOfInvoice();
        outputBoundary.getTotalInvoiceOfVietnamese(vietnameseTotal);
    }
}
