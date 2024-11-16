package org.example.domain.usecases;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.in.GetListInvoicesInputBoundary;
import org.example.domain.boundaries.out.GetListInvoicesOutputBoundary;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;

import java.util.List;

public class GetListInvoicesUseCase implements GetListInvoicesInputBoundary {
    private final GetListInvoicesOutputBoundary outputBoundary;
    private final VietnameseRepository vietnameseRepository;
    private final ForeignRepository foreignRepository;
    
    public GetListInvoicesUseCase(
            GetListInvoicesOutputBoundary outputBoundary,
            VietnameseRepository vietnameseRepository,
            ForeignRepository foreignRepository
    ) {
        this.outputBoundary = outputBoundary;
        this.vietnameseRepository = vietnameseRepository;
        this.foreignRepository = foreignRepository;
    }
    
    @Override
    public void execute() {
        List<VietnameseInvoice> vietnameseInvoices = vietnameseRepository.getInvoices();
        List<ForeignInvoice> foreignInvoices = foreignRepository.getInvoices();
        List<InvoiceDTO> allInvoices = new java.util.ArrayList<>();
        
        for (VietnameseInvoice vietnameseInvoice : vietnameseInvoices) {
            allInvoices.add(new InvoiceDTO(
                    vietnameseInvoice.getInvoiceId(),
                    vietnameseInvoice.getCustomerId(),
                    vietnameseInvoice.getFullName(),
                    vietnameseInvoice.getInvoiceDate(),
                    vietnameseInvoice.getCustomerType(),
                    vietnameseInvoice.getQuantity(),
                    vietnameseInvoice.getPrice(),
                    "Vietnam",
                    vietnameseInvoice.getQuota(),
                    vietnameseInvoice.calculateTotal()
            ));
        }
        
        for (ForeignInvoice foreignInvoice : foreignInvoices) {
            allInvoices.add(new InvoiceDTO(
                    foreignInvoice.getInvoiceId(),
                    foreignInvoice.getCustomerId(),
                    foreignInvoice.getFullName(),
                    foreignInvoice.getInvoiceDate(),
                    "none",
                    foreignInvoice.getQuantity(),
                    foreignInvoice.getPrice(),
                    foreignInvoice.getNationality(),
                    0,
                    foreignInvoice.calculateTotal()
            ));
        }
        
        outputBoundary.getListInvoices(allInvoices);
    }
}
