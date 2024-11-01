package org.example.domain.boundaries.out;

public interface OutputAnalystBoundary {
    public void setTotalInvoiceOfVietnamese(double totalInvoiceOfVietnamese);
    public void setTotalInvoiceOfForeign(double totalInvoiceOfForeign);
    public void setAverageTotalOfForeign(double averageTotalOfForeign);
    void renderData(double totalInvoiceOfVietnamese, double totalInvoiceOfForeign, double averageTotalOfForeign);
}
