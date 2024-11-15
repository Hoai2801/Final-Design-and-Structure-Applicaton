package org.example.domain.usecases;

import org.example.domain.boundaries.in.SearchInvoiceByNameInputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.SearchInvoiceByNameOutputBoundary;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;

import java.util.List;

public class SearchInvoiceByNameUseCase implements SearchInvoiceByNameInputBoundary {
    private final SearchInvoiceByNameOutputBoundary outputBoundary;
    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
    private final ForeignInvoiceRepository foreignInvoiceRepository;
    
    public SearchInvoiceByNameUseCase(SearchInvoiceByNameOutputBoundary outputBoundary, VietnameseInvoiceRepository vietnameseInvoiceRepository, ForeignInvoiceRepository foreignInvoiceRepository) {
        this.outputBoundary = outputBoundary;
        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
        this.foreignInvoiceRepository = foreignInvoiceRepository;
    }
    @Override
    public void execute(String name) {
        List<VietnameseInvoice> vietnameseInvoices = vietnameseInvoiceRepository.findInvoices(name);
        List<ForeignInvoice> foreignInvoices = foreignInvoiceRepository.findInvoices(name);
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
        
        outputBoundary.showSearchInvoiceByName(allInvoices);
    }
}
