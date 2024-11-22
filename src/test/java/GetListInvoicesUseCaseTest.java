import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.GetListInvoicesOutputBoundary;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.usecases.GetListInvoicesUseCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GetListInvoicesUseCaseTest {


    // Executes successfully when both repositories return non-empty lists
    @Test
    public void test_execute_with_non_empty_lists() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetListInvoicesOutputBoundary outputBoundary = invoices -> assertFalse(invoices.isEmpty());
        GetListInvoicesUseCase useCase = new GetListInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Handles empty lists from both repositories gracefully
    @Test
    public void test_handle_empty_lists_gracefully() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<VietnameseInvoice> getInvoices() {
                return new ArrayList<>();
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<ForeignInvoice> getInvoices() {
                return new ArrayList<>();
            }
        };
        GetListInvoicesOutputBoundary outputBoundary = invoices -> assertTrue(invoices.isEmpty());
        GetListInvoicesUseCase useCase = new GetListInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Handles null return from vietnameseRepository.getInvoices
    @Test
    public void test_handle_null_vietnamese_invoices() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public List<VietnameseInvoice> getInvoices() {
                return null;
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository();
        GetListInvoicesOutputBoundary outputBoundary = invoices -> assertNotNull(invoices);
        GetListInvoicesUseCase useCase = new GetListInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }

    // Handles null return from foreignRepository.getInvoices
    @Test
    public void test_handle_null_foreign_invoices() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository() {
            @Override
            public List<ForeignInvoice> getInvoices() {
                return null;
            }
        };
        GetListInvoicesOutputBoundary outputBoundary = invoices -> assertNotNull(invoices);
        GetListInvoicesUseCase useCase = new GetListInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);
        useCase.execute();
    }
}