//package org.example.adapter.presenter;
//
//import org.example.adapter.observer.Publisher;
//import org.example.domain.boundaries.out.OutputInvoiceBoundary;
//import org.example.domain.entities.models.ListInvoiceResponseModel;
//import org.example.domain.entities.models.ResponseModel;
//
//public class Presenter extends Publisher implements OutputInvoiceBoundary {
//    private ResponseModel responseModel;
//    private ListInvoiceResponseModel invoices;
//    @Override
//    public void showMessage(ResponseModel message) {
//        this.responseModel = message;
//        notifyMessageObservers(message);
//    }
//
//    @Override
//    public void showReport(ListInvoiceResponseModel invoices) {
//        generateReport(invoices);
//    }
//
//    @Override
//    public void showError(ResponseModel error) {
//        this.responseModel = error;
//        notifyErrorObservers(error);
//    }
//
//    public ResponseModel getResponseModel() {
//        return responseModel;
//    }
//
//    public ListInvoiceResponseModel getInvoices() {
//        return invoices;
//    }
//}
