import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.UpdateInvoiceOutputBoundary;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.ValidResult;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;
import org.example.domain.usecases.UpdateInvoiceUseCase;
import org.example.domain.util.Valid;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class UpdateInvoiceUseCaseTest {


    // Successfully update a Vietnamese invoice when all fields are valid
    @Test
    public void test_update_vietnamese_invoice_success() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseInvoiceRepository = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignInvoiceRepository = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(1, 123, "John Doe", "sinh hoat", "Vietnam", LocalDate.now().minusDays(1), 10, 100, 5);
        ValidResult validResult = new ValidResult(true, "Valid");
        VietnameseInvoice existingInvoice = new VietnameseInvoice(1, 123, "John Doe", LocalDate.now().minusDays(1), 10, 100, "sinh hoat", 5);

        when(valid.valid(requestModel)).thenReturn(validResult);
        when(vietnameseInvoiceRepository.getInvoiceById(1)).thenReturn(existingInvoice);
        when(vietnameseInvoiceRepository.updateInvoice(existingInvoice)).thenReturn(true);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseInvoiceRepository, foreignInvoiceRepository, valid);
        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(true, "Invoice updated successfully"));
    }

    // Successfully update a foreign invoice when all fields are valid
    @Test
    public void test_update_foreign_invoice_success() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseInvoiceRepository = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignInvoiceRepository = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(2, 456, "Jane Doe", "business", "USA", LocalDate.now().minusDays(1), 20, 200, 0);
        ValidResult validResult = new ValidResult(true, "Valid");
        ForeignInvoice existingInvoice = new ForeignInvoice(2, 456, "Jane Doe", LocalDate.now().minusDays(1), 20, 200, "USA");

        when(valid.valid(requestModel)).thenReturn(validResult);
        when(foreignInvoiceRepository.getInvoiceById(2)).thenReturn(existingInvoice);
        when(foreignInvoiceRepository.updateInvoice(existingInvoice)).thenReturn(true);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseInvoiceRepository, foreignInvoiceRepository, valid);
        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(true, "Invoice updated successfully"));
    }

    // Return success message when invoice update is successful
    @Test
    public void test_success_message_on_update() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseInvoiceRepository = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignInvoiceRepository = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(3, 789, "Alice Smith", "business", "Vietnam", LocalDate.now().minusDays(1), 15, 150, 10);
        ValidResult validResult = new ValidResult(true, "Valid");
        VietnameseInvoice existingInvoice = new VietnameseInvoice(3, 789, "Alice Smith", LocalDate.now().minusDays(1), 15, 150, "business", 10);

        when(valid.valid(requestModel)).thenReturn(validResult);
        when(vietnameseInvoiceRepository.getInvoiceById(3)).thenReturn(existingInvoice);
        when(vietnameseInvoiceRepository.updateInvoice(existingInvoice)).thenReturn(true);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseInvoiceRepository, foreignInvoiceRepository, valid);
        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(true, "Invoice updated successfully"));
    }

    // Validate request model before updating invoice
    @Test
    public void test_validate_request_model_before_update() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseInvoiceRepository = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignInvoiceRepository = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(-1, -1, "", "", "", null, -1, -1, -1);
        ValidResult validResult = new ValidResult(false, "Invalid data");

        when(valid.valid(requestModel)).thenReturn(validResult);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseInvoiceRepository, foreignInvoiceRepository, valid);
        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(false, "Invalid data"));
    }

    // Use appropriate repository based on nationality
    @Test
    public void test_use_correct_repository_based_on_nationality() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel vietnamRequest = new RequestModel(4, 1010, "Bob Brown", "sinh hoat", "Vietnam", LocalDate.now().minusDays(1), 5, 50, 3);
        RequestModel foreignRequest = new RequestModel(5, 2020, "Charlie Green", "business", "USA", LocalDate.now().minusDays(1), 8, 80, 0);

        ValidResult validResult = new ValidResult(true, "Valid");

        when(valid.valid(vietnamRequest)).thenReturn(validResult);
        when(valid.valid(foreignRequest)).thenReturn(validResult);

        VietnameseInvoice vietnameseExisting = new VietnameseInvoice(4, 1010, "Bob Brown", LocalDate.now().minusDays(1), 5, 50, "sinh hoat", 3);
        ForeignInvoice foreignExisting = new ForeignInvoice(5, 2020, "Charlie Green", LocalDate.now().minusDays(1), 8, 80, "USA");

        when(vietnameseRepo.getInvoiceById(4)).thenReturn(vietnameseExisting);
        when(foreignRepo.getInvoiceById(5)).thenReturn(foreignExisting);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        useCase.updateInvoice(vietnamRequest);
        verify(vietnameseRepo).updateInvoice(vietnameseExisting);

        useCase.updateInvoice(foreignRequest);
        verify(foreignRepo).updateInvoice(foreignExisting);
    }

    // Handle case when invoice ID does not exist in the repository
    @Test
    public void test_handle_nonexistent_invoice_id() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(9999, 3030, "Non Existent", "sinh hoat", "Vietnam", LocalDate.now().minusDays(1), 10, 100, 5);

        ValidResult validResult = new ValidResult(true, "Valid");

        when(valid.valid(requestModel)).thenReturn(validResult);
        when(vietnameseRepo.getInvoiceById(9999)).thenReturn(null);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(false, "Invoice not found"));
    }

    // Handle invalid request model with missing or incorrect fields
    @Test
    public void test_handle_invalid_request_model() {
        UpdateInvoiceOutputBoundary outputBoundary = mock(UpdateInvoiceOutputBoundary.class);
        VietnameseInvoiceRepository vietnameseRepo = mock(VietnameseInvoiceRepository.class);
        ForeignInvoiceRepository foreignRepo = mock(ForeignInvoiceRepository.class);
        Valid valid = mock(Valid.class);

        RequestModel requestModel = new RequestModel(-1, -1, "", "", "", null, -1.0, -1.0, -1.0);

        ValidResult validResult = new ValidResult(false, "Invalid data");

        when(valid.valid(requestModel)).thenReturn(validResult);

        UpdateInvoiceUseCase useCase = new UpdateInvoiceUseCase(outputBoundary, vietnameseRepo, foreignRepo, valid);

        useCase.updateInvoice(requestModel);

        verify(outputBoundary).update(new ResponseModel(false, "Invalid data"));
    }
}