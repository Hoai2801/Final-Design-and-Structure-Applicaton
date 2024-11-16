package org.example.domain.usecases;

import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.in.GetAnalystInputBoundary;
import org.example.domain.boundaries.out.GetAnalystOutputBoundary;
import org.example.domain.entities.models.AnalystResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAnalystUseCase implements GetAnalystInputBoundary {
    private final GetAnalystOutputBoundary outputBoundary;
    private final VietnameseRepository vietnameseRepository;
    private final ForeignRepository foreignRepository;
    
    public GetAnalystUseCase(GetAnalystOutputBoundary outputBoundary, VietnameseRepository vietnameseRepository, ForeignRepository foreignRepository) {
        this.outputBoundary = outputBoundary;
        this.vietnameseRepository = vietnameseRepository;
        this.foreignRepository = foreignRepository;
    }
    @Override
    public void execute() {
        // Fetch data from repositories
        List<Object[]> vietnameseData = vietnameseRepository.countInvoicesByMonth();
        List<Object[]> foreignData = foreignRepository.countInvoicesByMonth();

        // Combine results
        Map<String, Integer> invoiceCountsByMonthVietnamese = new HashMap<>();
        Map<String, Integer> invoiceCountsByMonthForeign = new HashMap<>();

        // Process Vietnamese invoices
        for (Object[] record : vietnameseData) {
            String month = (String) record[0];
            Integer count = ((Number) record[1]).intValue();
            invoiceCountsByMonthVietnamese.put(month, invoiceCountsByMonthVietnamese.getOrDefault(month, 0) + count);
        }

        // Process Foreign invoices
        for (Object[] record : foreignData) {
            String month = (String) record[0];
            Integer count = ((Number) record[1]).intValue();
            invoiceCountsByMonthForeign.put(month, invoiceCountsByMonthForeign.getOrDefault(month, 0) + count);
        }

        // Prepare output
        outputBoundary.showAnalyst(new AnalystResponse(invoiceCountsByMonthVietnamese, invoiceCountsByMonthForeign));
    }
}
