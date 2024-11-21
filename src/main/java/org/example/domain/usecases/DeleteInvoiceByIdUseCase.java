package org.example.domain.usecases;

import org.example.domain.boundaries.in.DeleteInvoiceByIdInputBoundary;
import org.example.domain.boundaries.out.DeleteInvoiceByIdOutputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.models.ResponseModel;

public class DeleteInvoiceByIdUseCase implements DeleteInvoiceByIdInputBoundary {
    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
    private final ForeignInvoiceRepository foreignInvoiceRepository;
    private final DeleteInvoiceByIdOutputBoundary outputBoundary;
    
    public DeleteInvoiceByIdUseCase(
            DeleteInvoiceByIdOutputBoundary outputBoundary,
            VietnameseInvoiceRepository vietnameseInvoiceRepository,
            ForeignInvoiceRepository foreignInvoiceRepository
    ) {
        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
        this.foreignInvoiceRepository = foreignInvoiceRepository;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void deleteInvoiceById(int invoiceId, String type) {
        boolean isSuccess;
        if (type.equals("Vietnam")) {
            isSuccess = vietnameseInvoiceRepository.deleteInvoiceById(invoiceId);
        } else {
            isSuccess = foreignInvoiceRepository.deleteInvoiceById(invoiceId);
        }
        if (isSuccess) {
            outputBoundary.deleteInvoice(new ResponseModel(true, "Invoice deleted successfully"));
        } else {
            throw new RuntimeException("Invoice deletion failed");
        }
    }
}
