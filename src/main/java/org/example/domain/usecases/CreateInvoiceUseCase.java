package org.example.domain.usecases;

import org.example.domain.boundaries.in.CreateInvoiceInputBoundary;
import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.Invoice;
import org.example.domain.entities.InvoiceFactory;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.util.Valid;

public class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private final CreateInvoiceOutputBoundary outputBoundary;
    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
    private final ForeignInvoiceRepository foreignInvoiceRepository;

    public CreateInvoiceUseCase(
            CreateInvoiceOutputBoundary outputBoundary,
            VietnameseInvoiceRepository vietnameseInvoiceRepository,
            ForeignInvoiceRepository foreignInvoiceRepository
    ) {
        this.outputBoundary = outputBoundary;
        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
        this.foreignInvoiceRepository = foreignInvoiceRepository;
    }

    @Override
    public void createInvoice(RequestModel req) {
        boolean valid = Valid.valid(req);
        if (valid) {
            Invoice invoice = InvoiceFactory.createInvoice(req.getCustomerId(), req.getFullName(), req.getInvoiceDate(), req.getCustomerType(), req.getQuantity(), req.getPrice(), req.getQuota(), req.getNationality());
            boolean result;
            if (invoice.getClass().equals(VietnameseInvoice.class)) {
                result = vietnameseInvoiceRepository.createInvoice((VietnameseInvoice) invoice);
            } else {
                result = foreignInvoiceRepository.createInvoice((ForeignInvoice) invoice);
            }
            if (result) {
                outputBoundary.onSuccess(new ResponseModel(true, "Invoice created successfully"));
            } else {
                outputBoundary.onError(new ResponseModel(false, "Invoice created failed"));
            }
        } else {
            outputBoundary.onError(new ResponseModel(false, "Invoice created failed"));
        }
    }
}
