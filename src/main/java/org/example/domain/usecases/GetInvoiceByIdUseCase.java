package org.example.domain.usecases;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.in.GetInvoiceByIdInputBoundary;
import org.example.domain.boundaries.out.GetInvoiceByIdOutputBoundary;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;

public class GetInvoiceByIdUseCase implements GetInvoiceByIdInputBoundary {
    private final GetInvoiceByIdOutputBoundary outputBoundary; 
    private final VietnameseRepository vietnameseRepository;
    private final ForeignRepository foreignRepository; 
    
    public GetInvoiceByIdUseCase(GetInvoiceByIdOutputBoundary outputBoundary, VietnameseRepository vietnameseRepository, ForeignRepository foreignRepository) {
        this.outputBoundary = outputBoundary;
        this.vietnameseRepository = vietnameseRepository;
        this.foreignRepository = foreignRepository;
    }
    
    @Override
    public void getInvoiceById(int invoiceId, String type) {
        if (type.equals("Vietnam")) {
            VietnameseInvoice invoice = vietnameseRepository.getInvoiceById(invoiceId);
            InvoiceDTO vietnameseInvoice = new InvoiceDTO(
                    invoice.getInvoiceId(),
                    invoice.getCustomerId(),
                    invoice.getFullName(),
                    invoice.getInvoiceDate(),
                    invoice.getCustomerType(),
                    invoice.getQuantity(),
                    invoice.getPrice(),
                    "Vietnam",
                    invoice.getQuota(),
                    invoice.calculateTotal()
            );
            outputBoundary.getInvoiceById(vietnameseInvoice);
        } else {
            ForeignInvoice invoice = foreignRepository.getInvoiceById(invoiceId);
            InvoiceDTO foreignInvoice = new InvoiceDTO(
                    invoice.getInvoiceId(),
                    invoice.getCustomerId(),
                    invoice.getFullName(),
                    invoice.getInvoiceDate(),
                    "none",
                    invoice.getQuantity(),
                    invoice.getPrice(),
                    invoice.getNationality(),
                    0,
                    invoice.calculateTotal()
            );
                    
            outputBoundary.getInvoiceById(foreignInvoice);
        }
    }
}
