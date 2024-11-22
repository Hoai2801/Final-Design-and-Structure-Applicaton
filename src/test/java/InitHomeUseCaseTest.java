
import org.example.domain.boundaries.in.GetListInvoicesInputBoundary;
import org.example.domain.boundaries.in.GetTotalInvoiceInputBoundary;
import org.example.domain.boundaries.in.GetTotalInvoiceOfForeignInputBoundary;
import org.example.domain.boundaries.in.GetTotalInvoiceOfVietnameseInputBoundary;
import org.example.domain.boundaries.out.InitHomeOutputBoundary;
import org.example.domain.usecases.InitHomeUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class InitHomeUseCaseTest {


    // Executes all input boundaries in sequence
    @Test
    public void test_execute_all_input_boundaries_in_sequence() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);
        useCase.execute();

        InOrder inOrder = inOrder(listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);
        inOrder.verify(listInvoices).execute();
        inOrder.verify(vietnameseInvoice).execute();
        inOrder.verify(foreignInvoice).execute();
        inOrder.verify(totalInvoice).execute();
    }

    // Calls initHome on the output boundary after executing input boundaries
    @Test
    public void test_calls_init_home_on_output_boundary() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);
        useCase.execute();

        verify(outputBoundary).initHome();
    }

    // Initializes with all required dependencies through constructor
    @Test
    public void test_initializes_with_all_dependencies() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        assertNotNull(useCase);
    }

    // Successfully integrates with all input and output boundaries
    @Test
    public void test_successful_integration_with_boundaries() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);
        useCase.execute();

        verify(listInvoices).execute();
        verify(vietnameseInvoice).execute();
        verify(foreignInvoice).execute();
        verify(totalInvoice).execute();
        verify(outputBoundary).initHome();
    }

    // Handles null input boundaries gracefully
    @Test
    public void test_handles_null_input_boundaries_gracefully() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);

        new InitHomeUseCase(outputBoundary, null, null, null, null);
    }

    // Manages exceptions thrown by any input boundary execution
    @Test
    public void test_manages_exceptions_from_input_boundaries() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        doThrow(new RuntimeException()).when(listInvoices).execute();

        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        try {
            useCase.execute();
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected exception
        }
    }

    // Ensures outputBoundary.initHome is called even if an input boundary fails
    @Test
    public void test_output_boundary_called_even_if_input_fails() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        doThrow(new RuntimeException()).when(listInvoices).execute();

        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        try {
            useCase.execute();
        } catch (RuntimeException e) {
            // Expected exception
        }

        verify(outputBoundary).initHome();
    }

    // Deals with missing or incomplete data from input boundaries
    @Test
    public void test_deals_with_missing_data_from_input_boundaries() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);

        doAnswer(invocation -> {
            // Simulate missing data scenario
            return null;
        }).when(listInvoices).execute();

        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        useCase.execute();

        verify(outputBoundary).initHome();
    }

    // Verifies order of execution for input boundaries
    @Test
    public void test_verifies_order_of_execution_for_input_boundaries() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        useCase.execute();

        InOrder inOrder = inOrder(listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        inOrder.verify(listInvoices).execute();
        inOrder.verify(vietnameseInvoice).execute();
        inOrder.verify(foreignInvoice).execute();
        inOrder.verify(totalInvoice).execute();
    }

    // Confirms no additional methods are called on boundaries
    @Test
    public void test_no_additional_methods_called_on_boundaries() {
        InitHomeOutputBoundary outputBoundary = mock(InitHomeOutputBoundary.class);
        GetListInvoicesInputBoundary listInvoices = mock(GetListInvoicesInputBoundary.class);
        GetTotalInvoiceInputBoundary totalInvoice = mock(GetTotalInvoiceInputBoundary.class);
        GetTotalInvoiceOfVietnameseInputBoundary vietnameseInvoice = mock(GetTotalInvoiceOfVietnameseInputBoundary.class);
        GetTotalInvoiceOfForeignInputBoundary foreignInvoice = mock(GetTotalInvoiceOfForeignInputBoundary.class);

        InitHomeUseCase useCase = new InitHomeUseCase(outputBoundary, listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice);

        useCase.execute();

        verifyNoMoreInteractions(listInvoices, vietnameseInvoice, foreignInvoice, totalInvoice, outputBoundary);
    }
}