import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.GetTotalInvoiceOutputBoundary;
import org.example.domain.usecases.GetTotalInvoicesUseCase;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GetTotalInvoicesUseCaseTest {


    // Executes successfully when both repositories return valid totals
    @Test
    public void test_execute_success_with_valid_totals() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(anyInt());
    }

    // Correctly sums totals from both repositories
    @Test
    public void test_correct_sum_of_totals() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(5);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(10);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(15);
    }

    // Outputs the correct total invoice count
    @Test
    public void test_output_correct_total_invoice_count() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(7);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(3);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(10);
    }

    // Initializes with valid dependencies
    @Test
    public void test_initialization_with_valid_dependencies() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);

        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        assertNotNull(useCase);
    }

    // Calls the output boundary with the correct total
    @Test
    public void test_calls_output_boundary_with_correct_total() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(8);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(12);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(20);
    }

    // Handles zero invoices from both repositories
    @Test
    public void test_handles_zero_invoices_from_both_repositories() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(0);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(0);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(0);
    }

    // Manages null or uninitialized repositories gracefully
    @Test
    public void test_manages_null_repositories_gracefully() {
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);

        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, null, null);

        useCase.execute();
    }

    // Deals with negative invoice counts from repositories
    @Test
    public void test_deals_with_negative_invoice_counts() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(-5);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(-3);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(-8);
    }

    // Handles exceptions thrown by repositories
    @Test
    public void test_handles_exceptions_from_repositories() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenThrow(new RuntimeException("Database error"));
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();
    }

    // Manages large numbers of invoices without overflow
    @Test
    public void test_manages_large_numbers_without_overflow() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        when(vietnameseRepository.getTotalAmountOfInvoice()).thenReturn(Integer.MAX_VALUE - 1);
        when(foreignRepository.getTotalAmountOfInvoice()).thenReturn(1);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary).showTotalInvoices(Integer.MAX_VALUE);
    }

    // Validates that repositories are queried exactly once per execution
    @Test
    public void test_repositories_queried_once_per_execution() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(vietnameseRepository, times(1)).getTotalAmountOfInvoice();
        verify(foreignRepository, times(1)).getTotalAmountOfInvoice();
    }

    // Ensures output boundary is called once per execution
    @Test
    public void test_output_boundary_called_once_per_execution() {
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetTotalInvoiceOutputBoundary outputBoundary = mock(GetTotalInvoiceOutputBoundary.class);
        GetTotalInvoicesUseCase useCase = new GetTotalInvoicesUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.execute();

        verify(outputBoundary, times(1)).showTotalInvoices(anyInt());
    }
}