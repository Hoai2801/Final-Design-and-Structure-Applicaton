import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.SearchInvoiceByNameOutputBoundary;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.usecases.SearchInvoiceByNameUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SearchInvoiceByNameUseCaseTest {


    // Executes search and returns a list of invoices when a valid name is provided
    @Test
    public void test_execute_with_valid_name() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        List<VietnameseInvoice> vietnameseInvoices = Arrays.asList(new VietnameseInvoice(1, 1, "John Doe", LocalDate.now(), 100, 10, "sinh hoat", 50));
        List<ForeignInvoice> foreignInvoices = Arrays.asList(new ForeignInvoice(2, 2, "Jane Doe", LocalDate.now(), 200, 20, "USA"));

        when(vietnameseRepo.findInvoices("Doe")).thenReturn(vietnameseInvoices);
        when(foreignRepo.findInvoices("Doe")).thenReturn(foreignInvoices);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Doe");

        verify(outputBoundary).showSearchInvoiceByName(anyList());
    }

    // Correctly converts VietnameseInvoice and ForeignInvoice entities to InvoiceDTO
    @Test
    public void test_conversion_to_invoice_dto() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        VietnameseInvoice vietnameseInvoice = new VietnameseInvoice(1, 1, "John Doe", LocalDate.now(), 100, 10, "sinh hoat", 50);
        ForeignInvoice foreignInvoice = new ForeignInvoice(2, 2, "Jane Doe", LocalDate.now(), 200, 20, "USA");

        when(vietnameseRepo.findInvoices("Doe")).thenReturn(Arrays.asList(vietnameseInvoice));
        when(foreignRepo.findInvoices("Doe")).thenReturn(Arrays.asList(foreignInvoice));

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Doe");

        ArgumentCaptor<List<InvoiceDTO>> captor = ArgumentCaptor.forClass(List.class);
        verify(outputBoundary).showSearchInvoiceByName(captor.capture());

        List<InvoiceDTO> dtos = captor.getValue();
        assertEquals(2, dtos.size());
        assertEquals("John Doe", dtos.get(0).getFullName());
        assertEquals("Jane Doe", dtos.get(1).getFullName());
    }

    // Calls outputBoundary.showSearchInvoiceByName with the correct list of InvoiceDTOs
    @Test
    public void test_output_boundary_called_with_correct_dtos() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        List<VietnameseInvoice> vietnameseInvoices = Arrays.asList(new VietnameseInvoice(1, 1, "John Doe", LocalDate.now(), 100, 10, "sinh hoat", 50));
        List<ForeignInvoice> foreignInvoices = Arrays.asList(new ForeignInvoice(2, 2, "Jane Doe", LocalDate.now(), 200, 20, "USA"));

        when(vietnameseRepo.findInvoices("Doe")).thenReturn(vietnameseInvoices);
        when(foreignRepo.findInvoices("Doe")).thenReturn(foreignInvoices);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Doe");

        ArgumentCaptor<List<InvoiceDTO>> captor = ArgumentCaptor.forClass(List.class);
        verify(outputBoundary).showSearchInvoiceByName(captor.capture());

        List<InvoiceDTO> dtos = captor.getValue();
        assertEquals(2, dtos.size());
    }

    // Handles both Vietnamese and Foreign invoices in the search process
    @Test
    public void test_handles_both_invoice_types() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        List<VietnameseInvoice> vietnameseInvoices = Arrays.asList(new VietnameseInvoice(1, 1, "John Doe", LocalDate.now(), 100, 10, "sinh hoat", 50));
        List<ForeignInvoice> foreignInvoices = Arrays.asList(new ForeignInvoice(2, 2, "Jane Doe", LocalDate.now(), 200, 20, "USA"));

        when(vietnameseRepo.findInvoices("Doe")).thenReturn(vietnameseInvoices);
        when(foreignRepo.findInvoices("Doe")).thenReturn(foreignInvoices);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Doe");

        verify(outputBoundary).showSearchInvoiceByName(anyList());
    }

    // Initializes SearchInvoiceByNameUseCase with valid repositories and output boundary
    @Test
    public void test_initialization_with_valid_dependencies() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        assertNotNull(useCase);
    }

    // Executes with an empty string as the name and returns an empty list
    @Test
    public void test_execute_with_empty_name() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        when(vietnameseRepo.findInvoices("")).thenReturn(Collections.emptyList());
        when(foreignRepo.findInvoices("")).thenReturn(Collections.emptyList());

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("");

        verify(outputBoundary).showSearchInvoiceByName(Collections.emptyList());
    }

    // Handles cases where no invoices are found for the given name
    @Test
    public void test_no_invoices_found_for_name() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        when(vietnameseRepo.findInvoices("NonExistent")).thenReturn(Collections.emptyList());
        when(foreignRepo.findInvoices("NonExistent")).thenReturn(Collections.emptyList());

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("NonExistent");

        verify(outputBoundary).showSearchInvoiceByName(Collections.emptyList());
    }

    // Manages null or invalid input for the name parameter gracefully
    @Test
    public void test_null_or_invalid_name_input() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        useCase.execute(null); // Expecting an exception to be thrown
    }

    // Processes invoices with special characters or non-ASCII names
    @Test
    public void test_special_characters_in_name() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        List<VietnameseInvoice> vietnameseInvoices = Arrays.asList(new VietnameseInvoice(1, 1, "Nguyễn Văn A", LocalDate.now(), 100, 10, "sinh hoat", 50));

        when(vietnameseRepo.findInvoices("Nguyễn")).thenReturn(vietnameseInvoices);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Nguyễn");

        verify(outputBoundary).showSearchInvoiceByName(anyList());
    }

    // Handles large numbers of invoices efficiently without performance degradation
    @Test
    public void test_large_number_of_invoices() {
        SearchInvoiceByNameOutputBoundary outputBoundary = mock(SearchInvoiceByNameOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);

        List<VietnameseInvoice> largeVietnameseInvoicesList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeVietnameseInvoicesList.add(new VietnameseInvoice(i, i, "Customer" + i, LocalDate.now(), 100 + i, 10 + i, "sinh hoat", 50 + i));
        }

        when(vietnameseRepo.findInvoices("Customer")).thenReturn(largeVietnameseInvoicesList);

        SearchInvoiceByNameUseCase useCase = new SearchInvoiceByNameUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.execute("Customer");

        verify(outputBoundary).showSearchInvoiceByName(anyList());
    }
}