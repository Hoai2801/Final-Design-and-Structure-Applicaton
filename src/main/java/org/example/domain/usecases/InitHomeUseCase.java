package org.example.domain.usecases;

import org.example.domain.boundaries.in.GetListInvoicesInputBoundary;
import org.example.domain.boundaries.in.GetTotalInvoiceInputBoundary;
import org.example.domain.boundaries.in.InitHomeInputBoundary;
import org.example.domain.boundaries.out.InitHomeOutputBoundary;

public class InitHomeUseCase implements InitHomeInputBoundary {
    private final InitHomeOutputBoundary outputBoundary;
    private final GetListInvoicesInputBoundary getListInvoicesInputBoundary;
    private final GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase;
    private final GetTotalInvoiceInputBoundary getTotalInvoiceInputBoundary;
    
    public InitHomeUseCase(InitHomeOutputBoundary outputBoundary, GetListInvoicesInputBoundary getListInvoicesInputBoundary, GetTotalInvoicesOfCustomerTypeUseCase getTotalInvoicesOfCustomerTypeUseCase, GetTotalInvoiceInputBoundary getTotalInvoiceInputBoundary) {
        this.outputBoundary = outputBoundary;
        this.getListInvoicesInputBoundary = getListInvoicesInputBoundary;
        this.getTotalInvoicesOfCustomerTypeUseCase = getTotalInvoicesOfCustomerTypeUseCase;
        this.getTotalInvoiceInputBoundary = getTotalInvoiceInputBoundary;
    }

    @Override
    public void execute() {
        getListInvoicesInputBoundary.execute();
        getTotalInvoicesOfCustomerTypeUseCase.execute();
        getTotalInvoiceInputBoundary.execute();
        outputBoundary.initHome();
    }
}
