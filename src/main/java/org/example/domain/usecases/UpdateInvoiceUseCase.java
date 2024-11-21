package org.example.domain.usecases;

import org.example.domain.boundaries.in.UpdateInvoiceInputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.UpdateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.util.Valid;

public class UpdateInvoiceUseCase implements UpdateInvoiceInputBoundary {
    private final UpdateInvoiceOutputBoundary outputBoundary;
    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
    private final ForeignInvoiceRepository foreignInvoiceRepository;
    
    public UpdateInvoiceUseCase(UpdateInvoiceOutputBoundary outputBoundary, VietnameseInvoiceRepository vietnameseInvoiceRepository, ForeignInvoiceRepository foreignInvoiceRepository) {
        this.outputBoundary = outputBoundary;
        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
        this.foreignInvoiceRepository = foreignInvoiceRepository;
    }
    
    @Override
    public void updateInvoice(RequestModel requestModel) {
        ValidResult validResult = Valid.valid(requestModel);
        if (!validResult.isValid()) {
            outputBoundary.update(new ResponseModel(false, validResult.getError()));
            return;
        }
        if (requestModel.getNationality().equals("Vietnam")) {
            VietnameseInvoice existingInvoice = vietnameseInvoiceRepository.getInvoiceById(requestModel.getInvoiceId());
            if (existingInvoice != null) {
                existingInvoice.setCustomerId(requestModel.getCustomerId());
                existingInvoice.setFullName(requestModel.getFullName());
                existingInvoice.setInvoiceDate(requestModel.getInvoiceDate());
                existingInvoice.setCustomerType(requestModel.getCustomerType());
                existingInvoice.setQuantity(requestModel.getQuantity());
                existingInvoice.setPrice(requestModel.getPrice());
                existingInvoice.setQuota(requestModel.getQuota());
                boolean result = vietnameseInvoiceRepository.updateInvoice(existingInvoice);
                if (result) {
                    outputBoundary.update(new ResponseModel(true, "Invoice updated successfully"));
                } else {
                    outputBoundary.update(new ResponseModel(false, "Invoice update failed"));
                }
            } else {
                outputBoundary.update(new ResponseModel(false, "Invoice not found"));
            }
        } else {
            ForeignInvoice existingInvoice = foreignInvoiceRepository.getInvoiceById(requestModel.getInvoiceId());
            if (existingInvoice != null) {
                existingInvoice.setCustomerId(requestModel.getCustomerId());
                existingInvoice.setFullName(requestModel.getFullName());
                existingInvoice.setNationality(requestModel.getNationality());
                existingInvoice.setInvoiceDate(requestModel.getInvoiceDate());
                existingInvoice.setQuantity(requestModel.getQuantity());
                existingInvoice.setPrice(requestModel.getPrice());
                boolean result = foreignInvoiceRepository.updateInvoice(existingInvoice);
                if (result) {
                    outputBoundary.update(new ResponseModel(true, "Invoice updated successfully"));
                } else {
                    outputBoundary.update(new ResponseModel(false, "Invoice update failed"));
                }
            } else {
                outputBoundary.update(new ResponseModel(false, "Invoice not found"));
            }
        }
    }
}
