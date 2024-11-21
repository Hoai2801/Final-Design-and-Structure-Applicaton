package org.example.domain.usecases;

import org.example.domain.boundaries.in.*;
import org.example.domain.boundaries.out.GetTotalInvoiceOfForeignOutputBoundary;
import org.example.domain.boundaries.out.GetTotalInvoiceOfVietnameseOutputBoundary;
import org.example.domain.boundaries.out.InitHomeOutputBoundary;

public class InitHomeUseCase implements InitHomeInputBoundary {
    private final InitHomeOutputBoundary outputBoundary;
    private final GetListInvoicesInputBoundary getListInvoicesInputBoundary;
    private final GetTotalInvoiceInputBoundary getTotalInvoiceInputBoundary;
    private final GetTotalInvoiceOfVietnameseInputBoundary getTotalInvoiceOfVietnameseInputBoundary;
    private final GetTotalInvoiceOfForeignInputBoundary getTotalInvoiceOfForeignInputBoundary;
    
    public InitHomeUseCase(
            InitHomeOutputBoundary outputBoundary, 
            GetListInvoicesInputBoundary getListInvoicesInputBoundary,
            GetTotalInvoiceOfVietnameseInputBoundary getTotalInvoiceOfVietnameseInputBoundary, 
            GetTotalInvoiceOfForeignInputBoundary getTotalInvoiceOfForeignInputBoundary, 
            GetTotalInvoiceInputBoundary getTotalInvoiceInputBoundary
    ) {
        this.outputBoundary = outputBoundary;
        this.getListInvoicesInputBoundary = getListInvoicesInputBoundary;
        this.getTotalInvoiceInputBoundary = getTotalInvoiceInputBoundary;
        this.getTotalInvoiceOfVietnameseInputBoundary = getTotalInvoiceOfVietnameseInputBoundary;
        this.getTotalInvoiceOfForeignInputBoundary = getTotalInvoiceOfForeignInputBoundary;
    }

    @Override
    public void execute() {
        getListInvoicesInputBoundary.execute();
        getTotalInvoiceOfVietnameseInputBoundary.execute();
        getTotalInvoiceOfForeignInputBoundary.execute();
        getTotalInvoiceInputBoundary.execute();
        outputBoundary.initHome();
    }
}
