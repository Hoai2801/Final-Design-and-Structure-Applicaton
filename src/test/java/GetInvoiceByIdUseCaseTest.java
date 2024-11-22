import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.GetInvoiceByIdOutputBoundary;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.usecases.GetInvoiceByIdUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetInvoiceByIdUseCaseTest {


    // Retrieves a Vietnamese invoice by ID and processes it correctly
    @Test
    public void test_retrieve_vietnamese_invoice() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now(), 100, 10.0, "sinh hoat", 50);
        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        verify(outputBoundary).getInvoiceById(any(InvoiceDTO.class));
    }

    // Retrieves a Foreign invoice by ID and processes it correctly
    @Test
    public void test_retrieve_foreign_invoice() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        ForeignInvoice invoice = new ForeignInvoice(1, 123, "Jane Doe", LocalDate.now(), 100, 10.0, "USA");
        when(foreignRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Foreign");

        verify(outputBoundary).getInvoiceById(any(InvoiceDTO.class));
    }

    // Correctly converts VietnameseInvoice to InvoiceDTO
    @Test
    public void test_convert_vietnamese_invoice_to_dto() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now(), 100, 10.0, "sinh hoat", 50);
        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        ArgumentCaptor<InvoiceDTO> captor = ArgumentCaptor.forClass(InvoiceDTO.class);
        verify(outputBoundary).getInvoiceById(captor.capture());
        InvoiceDTO dto = captor.getValue();

        assertEquals(invoice.getInvoiceId(), dto.getInvoiceId());
        assertEquals(invoice.getCustomerId(), dto.getCustomerId());
        assertEquals(invoice.getFullName(), dto.getFullName());
        assertEquals(invoice.getCustomerType(), dto.getCustomerType());
    }

    // Correctly converts ForeignInvoice to InvoiceDTO
    @Test
    public void test_convert_foreign_invoice_to_dto() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        ForeignInvoice invoice = new ForeignInvoice(1, 123, "Jane Doe", LocalDate.now(), 100, 10.0, "USA");
        when(foreignRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Foreign");

        ArgumentCaptor<InvoiceDTO> captor = ArgumentCaptor.forClass(InvoiceDTO.class);
        verify(outputBoundary).getInvoiceById(captor.capture());
        InvoiceDTO dto = captor.getValue();

        assertEquals(invoice.getNationality(), dto.getNationality());
    }

    // Calls outputBoundary with the correct InvoiceDTO for Vietnamese invoices
    @Test
    public void test_output_boundary_called_for_vietnamese_invoice() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now(), 100, 10.0, "sinh hoat", 50);
        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        verify(outputBoundary).getInvoiceById(any(InvoiceDTO.class));
    }

    // Calls outputBoundary with the correct InvoiceDTO for Foreign invoices
    @Test
    public void test_output_boundary_called_for_foreign_invoice() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        ForeignInvoice invoice = new ForeignInvoice(1, 123, "Jane Doe", LocalDate.now(), 100, 10.0, "USA");
        when(foreignRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Foreign");

        verify(outputBoundary).getInvoiceById(any(InvoiceDTO.class));
    }

    // Handles null invoice returned from VietnameseRepository gracefully
    @Test
    public void test_null_vietnamese_invoice_handling() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        when(vietnameseRepository.getInvoiceById(1)).thenReturn(null);

        useCase.getInvoiceById(1, "Vietnam");

        verify(outputBoundary, never()).getInvoiceById(any(InvoiceDTO.class));
    }

    // Handles null invoice returned from ForeignRepository gracefully
    @Test
    public void test_null_foreign_invoice_handling() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        when(foreignRepository.getInvoiceById(1)).thenReturn(null);

        useCase.getInvoiceById(1, "Foreign");

        verify(outputBoundary, never()).getInvoiceById(any(InvoiceDTO.class));
    }

    // Handles invalid invoice type gracefully
    @Test
    public void test_invalid_invoice_type_handling() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "InvalidType");

        verify(outputBoundary, never()).getInvoiceById(any(InvoiceDTO.class));
    }

    // Processes invoices with zero quantity correctly
    @Test
    public void test_zero_quantity_processing() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now(), 0, 10.0, "sinh hoat", 50);
        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        ArgumentCaptor<InvoiceDTO> captor = ArgumentCaptor.forClass(InvoiceDTO.class);
        verify(outputBoundary).getInvoiceById(captor.capture());

        assertEquals(0.0, captor.getValue().getTotal(), 0.01);
    }

    // Processes invoices with zero price correctly
    @Test
    public void test_zero_price_processing() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now(), 100, 0.0, "sinh hoat", 50);
        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        ArgumentCaptor<InvoiceDTO> captor = ArgumentCaptor.forClass(InvoiceDTO.class);
        verify(outputBoundary).getInvoiceById(captor.capture());

        assertEquals(0.0, captor.getValue().getTotal(), 0.01);
    }

    // Handles missing or null fields in Vietnamese Invoice
    @Test
    public void test_missing_fields_in_vietnamese_invoice_handling() {
        GetInvoiceByIdOutputBoundary outputBoundary = mock(GetInvoiceByIdOutputBoundary.class);
        VietnameseRepository vietnameseRepository = mock(VietnameseRepository.class);
        ForeignRepository foreignRepository = mock(ForeignRepository.class);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        VietnameseInvoice invoice = new VietnameseInvoice();

        when(vietnameseRepository.getInvoiceById(1)).thenReturn(invoice);

        useCase.getInvoiceById(1, "Vietnam");

        verify(outputBoundary).getInvoiceById(any(InvoiceDTO.class));
    }
}