//package org.example.domain.interactors;
//
//import org.example.domain.boundaries.in.InputInvoiceBoundary;
//import org.example.domain.boundaries.out.ForeignInvoiceRepository;
//import org.example.domain.boundaries.out.OutputInvoiceBoundary;
//import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
//import org.example.domain.entities.ForeignInvoice;
//import org.example.domain.entities.VietnameseInvoice;
//import org.example.domain.entities.dtos.InvoiceDTO;
//import org.example.domain.entities.dtos.ValidResult;
//import org.example.domain.entities.models.ListInvoiceResponseModel;
//import org.example.domain.entities.models.RequestModel;
//import org.example.domain.entities.models.ResponseModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Interactor implements InputInvoiceBoundary {
//    private final VietnameseInvoiceRepository vietnameseInvoiceRepository;
//    private final ForeignInvoiceRepository foreignInvoiceRepository;
//    private final OutputInvoiceBoundary outputInvoiceBoundary;
//
//    public Interactor(VietnameseInvoiceRepository vietnameseInvoiceRepository,
//            ForeignInvoiceRepository foreignInvoiceRepository, OutputInvoiceBoundary outputInvoiceBoundary) {
//        this.vietnameseInvoiceRepository = vietnameseInvoiceRepository;
//        this.foreignInvoiceRepository = foreignInvoiceRepository;
//        this.outputInvoiceBoundary = outputInvoiceBoundary;
//    }
//
//    @Override
//    public void createInvoice(RequestModel req) {
//        ValidResult valid = valid(req);
//        if (!valid.isValid()) {
//            outputInvoiceBoundary.showError(new ResponseModel(valid.getError()));
//        } else {
//            boolean result;
//            if (req.getNationality().equals("Vietnam")) {
//                result = vietnameseInvoiceRepository.createInvoice(
//                        new VietnameseInvoice(
//                                req.getCustomerId(),
//                                req.getFullName(),
//                                req.getInvoiceDate(),
//                                req.getCustomerType(),
//                                req.getQuantity(),
//                                req.getPrice(),
//                                req.getQuota()));
//            } else {
//                result = foreignInvoiceRepository.createInvoice(
//                        new ForeignInvoice(
//                                req.getCustomerId(),
//                                req.getFullName(),
//                                req.getNationality(),
//                                req.getInvoiceDate(),
//                                req.getQuantity(),
//                                req.getPrice()));
//            }
//            if (result) {
//                outputInvoiceBoundary.showMessage(new ResponseModel("Invoice created successfully"));
//            } else {
//                outputInvoiceBoundary.showMessage(new ResponseModel("Invoice creation failed"));
//            }
//        }
//
//    }
//
//    @Override
//    public void deleteInvoice(RequestModel req) {
//        boolean result;
//        if (req.getNationality().equals("Vietnam")) {
//            result = vietnameseInvoiceRepository.deleteInvoice(
//                    new VietnameseInvoice(
//                            req.getCustomerId(),
//                            req.getFullName(),
//                            req.getInvoiceDate(),
//                            req.getCustomerType(),
//                            req.getQuantity(),
//                            req.getPrice(),
//                            req.getQuota()));
//        } else {
//            result = foreignInvoiceRepository.deleteInvoice(
//                    new ForeignInvoice(
//                            req.getCustomerId(), 
//                            req.getFullName(),
//                            req.getNationality(),
//                            req.getInvoiceDate(),
//                            req.getQuantity(),
//                            req.getPrice()));
//        }
//        if (result) {
//            outputInvoiceBoundary.showMessage(new ResponseModel("Invoice deleted successfully"));
//        } else {
//            outputInvoiceBoundary.showMessage(new ResponseModel("Invoice deletion failed"));
//        }
//    }
//
//    @Override
//    public void updateInvoice(RequestModel req) {
//        boolean result;
//        if (req.getNationality().equals("Vietnam")) {
//            result = vietnameseInvoiceRepository.updateInvoice(
//                    new VietnameseInvoice(
//                            req.getCustomerId(),
//                            req.getFullName(),
//                            req.getInvoiceDate(),
//                            req.getCustomerType(),
//                            req.getQuantity(),
//                            req.getPrice(),
//                            req.getQuota()));
//        } else {
//            result = foreignInvoiceRepository.updateInvoice(
//                    new ForeignInvoice(
//                            req.getCustomerId(),
//                            req.getFullName(),
//                            req.getNationality(),
//                            req.getInvoiceDate(),
//                            req.getQuantity(),
//                            req.getPrice()));
//        }
//        if (result) {
//            outputInvoiceBoundary.showMessage(new ResponseModel("Invoice updated successfully"));
//        } else {
//            outputInvoiceBoundary.showMessage(new ResponseModel("Invoice update failed"));
//        }
//    }
//
//    @Override
//    public void getTotalAmountOfInvoice(int type) {
//        if (type == 1) {
//            outputInvoiceBoundary.showMessage(
//                    new ResponseModel("Total amount: " + vietnameseInvoiceRepository.getTotalAmountOfInvoice()));
//        } else if (type == 2) {
//            outputInvoiceBoundary.showMessage(
//                    new ResponseModel("Total amount: " + foreignInvoiceRepository.getTotalAmountOfInvoice()));
//        }
//    }
//
//    @Override
//    public void getTotalAmountOfInvoiceByMonth(int month) {
//        if (month == 1) {
//            outputInvoiceBoundary.showMessage(new ResponseModel(
//                    "Total amount: " + vietnameseInvoiceRepository.getTotalAmountOfInvoiceByMonth(month)));
//        } else if (month == 2) {
//            outputInvoiceBoundary.showMessage(new ResponseModel(
//                    "Total amount: " + foreignInvoiceRepository.getTotalAmountOfInvoiceByMonth(month)));
//        }
//    }
//
//    @Override
//    public void getInvoices() {
//        List<VietnameseInvoice> vietnameseInvoices = vietnameseInvoiceRepository.getInvoices();
//        List<ForeignInvoice> foreignInvoices = foreignInvoiceRepository.getInvoices();
//        List<InvoiceDTO> allInvoices = new ArrayList<>();
//
//        for (VietnameseInvoice vietnameseInvoice : vietnameseInvoices) {
//            allInvoices.add(new InvoiceDTO(
//                    vietnameseInvoice.getCustomerId(),
//                    vietnameseInvoice.getFullName(),
//                    vietnameseInvoice.getInvoiceDate(),
//                    vietnameseInvoice.getCustomerType(),
//                    vietnameseInvoice.getQuantity(),
//                    vietnameseInvoice.getPrice(),
//                    "Vietnam", 
//                    vietnameseInvoice.getQuota(),
//                    vietnameseInvoice.calculateTotal()
//            ));
//        }
//
//        for (ForeignInvoice foreignInvoice : foreignInvoices) {
//            allInvoices.add(new InvoiceDTO(
//                    foreignInvoice.getCustomerId(),
//                    foreignInvoice.getFullName(),
//                    foreignInvoice.getInvoiceDate(),
//                    "Foreign",
//                    foreignInvoice.getQuantity(),
//                    foreignInvoice.getPrice(),
//                    foreignInvoice.getNationality(),
//                    0,
//                    foreignInvoice.calculateTotal()
//            ));
//        }
//
//        outputInvoiceBoundary.showReport(new ListInvoiceResponseModel(allInvoices));
//
//    }
//
//    public ValidResult valid(RequestModel req) {
//        ValidResult validResult = new ValidResult(true, null);
//        if (req.getFullName() == null) {
//            validResult.setValid(false);
//            validResult.setError("FullName cannot be empty");
//        }
//
//        if (req.getCustomerType() == null) {
//            validResult.setValid(false);
//            validResult.setError("Customer type cannot be empty");
//        }
//
//        if (req.getNationality() == null) {
//            validResult.setValid(false);
//            validResult.setError("Nationality type cannot be empty");
//        }
//
//        if (req.getCustomerId() <= 0) {
//            validResult.setValid(false);
//            validResult.setError("Invalid customer id");
//        }
//
//        if (req.getQuantity() <= 0) {
//            validResult.setValid(false);
//            validResult.setError("Invalid quantity");
//        }
//
//        if (req.getQuota() < 0) {
//            validResult.setValid(false);
//            validResult.setError("Invalid quota");
//        }
//
//        if (req.getPrice() <= 0) {
//            validResult.setValid(false);
//            validResult.setError("Invalid price");
//        }
//
//        return validResult;
//    }
//}
