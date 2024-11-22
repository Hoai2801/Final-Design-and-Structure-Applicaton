package org.example.domain.usecases;

import org.example.domain.boundaries.in.CreateInvoiceInputBoundary;
import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.Invoice;
import org.example.domain.entities.InvoiceFactory;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.util.Valid;

public class CreateInvoiceUseCase implements CreateInvoiceInputBoundary {
    private final CreateInvoiceOutputBoundary outputBoundary;
    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
    private final ForeignInvoiceRepository foreignInvoiceRepository;
    private final Valid valid;
    public CreateInvoiceUseCase(
            CreateInvoiceOutputBoundary outputBoundary,
            VietnameseInvoiceRepository vietnameseInvoiceRepository,
            ForeignInvoiceRepository foreignInvoiceRepository,
            Valid valid
    ) {
        this.outputBoundary = outputBoundary;
        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
        this.foreignInvoiceRepository = foreignInvoiceRepository;
        this.valid = valid;
    }

    @Override
    public void createInvoice(RequestModel req) {
        ValidResult validResult = valid.valid(req);
        if (!validResult.isValid()) {
            outputBoundary.onCreate(new ResponseModel(false, validResult.getError()));
            return;
        }
        Invoice invoice = InvoiceFactory.createInvoice(req.getCustomerId(), req.getFullName(), req.getInvoiceDate(), req.getCustomerType(), req.getQuantity(), req.getPrice(), req.getQuota(), req.getNationality());
        boolean result;
        if (invoice.getClass().equals(VietnameseInvoice.class)) {
            result = vietnameseInvoiceRepository.createInvoice((VietnameseInvoice) invoice);
        } else {
            result = foreignInvoiceRepository.createInvoice((ForeignInvoice) invoice);
        }
        if (result) {
            outputBoundary.onCreate(new ResponseModel(true, "Invoice created successfully"));
        } else {
            outputBoundary.onCreate(new ResponseModel(false, "Invoice creation failed")); 
        }
    }
}
