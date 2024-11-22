import org.example.domain.boundaries.out.CreateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.usecases.CreateInvoiceUseCase;
import org.example.domain.util.Valid;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateInvoiceUseCaseTest {


    // Successfully creates a Vietnamese invoice when valid Vietnamese data is provided
    @Test
    public void test_create_vietnamese_invoice_success() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "John Doe", "Regular", "Vietnam", LocalDate.now().minusDays(1), 10, 100.0, 5);
        when(valid.valid(request)).thenReturn(new ValidResult(true, ""));
        when(vietnameseRepo.createInvoice(any(VietnameseInvoice.class))).thenReturn(true);

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(true, "Invoice created successfully"));
    }

    // Successfully creates a foreign invoice when valid foreign data is provided
    @Test
    public void test_create_foreign_invoice_success() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "Jane Doe", "Regular", "USA", LocalDate.now().minusDays(1), 10, 100.0, 0);
        when(valid.valid(request)).thenReturn(new ValidResult(true, ""));
        when(foreignRepo.createInvoice(any(ForeignInvoice.class))).thenReturn(true);

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(true, "Invoice created successfully"));
    }

    // Returns success message when invoice creation is successful
    @Test
    public void test_success_message_on_creation() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "John Doe", "Regular", "Vietnam", LocalDate.now().minusDays(1), 10, 100.0, 5);
        when(valid.valid(request)).thenReturn(new ValidResult(true, ""));
        when(vietnameseRepo.createInvoice(any(VietnameseInvoice.class))).thenReturn(true);

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(true, "Invoice created successfully"));
    }

    // Validates request model before invoice creation
    @Test
    public void test_validate_request_model_before_creation() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, -1, "", "", "", null, -1, -1.0, -1);
        when(valid.valid(request)).thenReturn(new ValidResult(false, "Invalid data"));

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Invalid data"));
    }

    // Uses InvoiceFactory to create the correct invoice type based on nationality
    @Test
    public void test_invoice_factory_usage_based_on_nationality() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel vietnamRequest = new RequestModel(0, 1, "John Doe", "Regular", "Vietnam", LocalDate.now().minusDays(1), 10, 100.0, 5);
        RequestModel foreignRequest = new RequestModel(0, 1, "Jane Doe", "Regular", "USA", LocalDate.now().minusDays(1), 10, 100.0, 0);

        when(valid.valid(vietnamRequest)).thenReturn(new ValidResult(true, ""));
        when(valid.valid(foreignRequest)).thenReturn(new ValidResult(true, ""));

        useCase.createInvoice(vietnamRequest);
        verify(vietnameseRepo).createInvoice(any(VietnameseInvoice.class));

        useCase.createInvoice(foreignRequest);
        verify(foreignRepo).createInvoice(any(ForeignInvoice.class));
    }

    // Handles invalid request model data by returning an error message
    @Test
    public void test_invalid_request_model_returns_error_message() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, -1, "", "", "", null, -1, -1.0, -1);
        when(valid.valid(request)).thenReturn(new ValidResult(false, "Invalid data"));

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Invalid data"));
    }

    // Returns failure message when invoice creation fails in the repository
    @Test
    public void test_failure_message_on_creation_failure() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "John Doe", "Regular", "Vietnam", LocalDate.now().minusDays(1), 10, 100.0, 5);
        when(valid.valid(request)).thenReturn(new ValidResult(true, ""));
        when(vietnameseRepo.createInvoice(any(VietnameseInvoice.class))).thenReturn(false);

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Invoice creation failed"));
    }

    // Handles null or empty fields in the request model gracefully
    @Test
    public void test_null_or_empty_fields_handling() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "", "", "", null, 10.0, 100.0, 5.0);
        when(valid.valid(request)).thenReturn(new ValidResult(false, "Full name is required"));

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Full name is required"));
    }

    // Manages cases where invoice date is in the future
    @Test
    public void test_future_invoice_date_handling() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "John Doe", "Regular", "Vietnam", LocalDate.now().plusDays(1), 10.0, 100.0, 5.0);
        when(valid.valid(request)).thenReturn(new ValidResult(false, "Invoice date must be in the past"));

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Invoice date must be in the past"));
    }

    // Handles cases where nationality is not recognized
    @Test
    public void test_unrecognized_nationality_handling() {
        CreateInvoiceOutputBoundary outputBoundary = mock(CreateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);
        CreateInvoiceUseCase useCase = new CreateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        RequestModel request = new RequestModel(0, 1, "John Doe", "Regular", "UnknownCountry", LocalDate.now().minusDays(1), 10.0, 100.0, 5.0);
        when(valid.valid(request)).thenReturn(new ValidResult(false, "Nationality is invalid"));

        useCase.createInvoice(request);

        verify(outputBoundary).onCreate(new ResponseModel(false, "Nationality is invalid"));
    }
}