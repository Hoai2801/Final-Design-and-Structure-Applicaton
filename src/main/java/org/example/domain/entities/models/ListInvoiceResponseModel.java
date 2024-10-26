package org.example.domain.entities.models;

import org.example.domain.entities.dtos.InvoiceDTO;

import java.util.List;

public class ListInvoiceResponseModel {

    private List<InvoiceDTO> invoices;

    public ListInvoiceResponseModel(List<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceDTO> getInvoices() {
        return invoices;
    }
    
    public void setInvoices(List<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }
}
