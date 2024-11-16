package org.example.domain.entities.models;

import java.util.Map;

public class AnalystResponse {
    Map<String, Integer> invoiceCountsByMonthVietnamese;
    Map<String, Integer> invoiceCountsByMonthForeign;
    
    public AnalystResponse(
            Map<String, Integer> invoiceCountsByMonthVietnamese,
            Map<String, Integer> invoiceCountsByMonthForeign
    ) {
        this.invoiceCountsByMonthVietnamese = invoiceCountsByMonthVietnamese;
        this.invoiceCountsByMonthForeign = invoiceCountsByMonthForeign;
    }
    
    public Map<String, Integer> getInvoiceCountsByMonthVietnamese() {
        return invoiceCountsByMonthVietnamese;
    }
    
    public Map<String, Integer> getInvoiceCountsByMonthForeign() {
        return invoiceCountsByMonthForeign;
    } 
}
