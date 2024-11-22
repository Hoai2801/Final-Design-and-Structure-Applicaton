import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.DeleteInvoiceByIdOutputBoundary;
import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.usecases.DeleteInvoiceByIdUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteInvoiceByIdUseCaseTest {


    // Successfully delete a Vietnamese invoice by ID
    @Test
    public void test_delete_vietnamese_invoice_success() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertTrue(responseModel.isSuccess());
            assertEquals("Invoice deleted successfully", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(1, "Vietnam");
    }

    // Successfully delete a Foreign invoice by ID
    @Test
    public void test_delete_foreign_invoice_success() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertTrue(responseModel.isSuccess());
            assertEquals("Invoice deleted successfully", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(1, "Foreign");
    }

    // Return success message when invoice deletion is successful
    @Test
    public void test_success_message_on_deletion() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseInvoiceRepository() {
            @Override
            public boolean createInvoice(VietnameseInvoice invoice) {
                return false;
            }

            @Override
            public int getTotalAmountOfInvoice() {
                return 0;
            }

            @Override
            public List<VietnameseInvoice> findInvoices(String name) {
                return List.of();
            }

            @Override
            public List<VietnameseInvoice> getInvoices() {
                return List.of();
            }

            @Override
            public boolean deleteInvoiceById(int invoiceId) {
                return true;
            }

            @Override
            public VietnameseInvoice getInvoiceById(int invoiceId) {
                return null;
            }

            @Override
            public boolean updateInvoice(VietnameseInvoice existingInvoice) {
                return false;
            }

            @Override
            public List<Object[]> countInvoicesByMonth() {
                return List.of();
            }
        };
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertTrue(responseModel.isSuccess());
            assertEquals("Invoice deleted successfully", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(1, "Vietnam");
    }

    // Return failure message when invoice deletion fails
    @Test
    public void test_failure_message_on_deletion() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertFalse(responseModel.isSuccess());
            assertEquals("Invoice delete failed", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(1, "Vietnam");
    }

    // Attempt to delete an invoice with an invalid ID
    @Test
    public void test_delete_with_invalid_id() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertFalse(responseModel.isSuccess());
            assertEquals("Invoice delete failed", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(-1, "Vietnam");
    }

    // Attempt to delete an invoice with a non-existent ID
    @Test
    public void test_delete_with_non_existent_id() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertFalse(responseModel.isSuccess());
            assertEquals("Invoice delete failed", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);
        useCase.deleteInvoiceById(9999, "Vietnam");
    }

    // Handle null or empty string for the type parameter
    @Test
    public void test_handle_null_or_empty_type() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertFalse(responseModel.isSuccess());
            assertEquals("Invoice delete failed", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        useCase.deleteInvoiceById(1, null);

        useCase.deleteInvoiceById(1, "");
    }

    // Handle unexpected type values other than "Vietnam" or "Foreign"
    @Test
    public void test_handle_unexpected_type_values() {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();
        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            assertFalse(responseModel.isSuccess());
            assertEquals("Invoice delete failed", responseModel.getMessage());
        };
        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        useCase.deleteInvoiceById(1, "Domestic");

        useCase.deleteInvoiceById(1, "International");
    }

    // Ensure correct repository is called based on the type
    @Test
    public void test_correct_repository_called_based_on_type() {
        final boolean[] vietnamCalled = {false};
        final boolean[] foreignCalled = {false};

        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();

        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {};

        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        useCase.deleteInvoiceById(1, "Vietnam");

        assertTrue(vietnamCalled[0]);

        useCase.deleteInvoiceById(1, "Foreign");

        assertTrue(foreignCalled[0]);
    }

    // Verify that the output boundary is always invoked
    @Test
    public void test_output_boundary_invoked_always() {
        final boolean[] outputInvoked = {false};

        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();

        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> outputInvoked[0] = true;

        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        useCase.deleteInvoiceById(1, "Vietnam");

        assertTrue(outputInvoked[0]);

        outputInvoked[0] = false;

        useCase.deleteInvoiceById(1, "Foreign");

        assertTrue(outputInvoked[0]);
    }

    // Test concurrent deletion requests for the same invoice ID
    @Test
    public void test_concurrent_deletion_requests_same_id() throws InterruptedException {
        VietnameseInvoiceRepository vietnameseRepo = new VietnameseRepository();
        ForeignInvoiceRepository foreignRepo = new ForeignRepository();

        final int[] successCount = {0};

        DeleteInvoiceByIdOutputBoundary outputBoundary = responseModel -> {
            if (responseModel.isSuccess()) successCount[0]++;
        };

        DeleteInvoiceByIdUseCase useCase = new DeleteInvoiceByIdUseCase(outputBoundary, vietnameseRepo, foreignRepo);

        Thread thread1 = new Thread(() -> useCase.deleteInvoiceById(1, "Vietnam"));

        Thread thread2 = new Thread(() -> useCase.deleteInvoiceById(1, "Vietnam"));

        thread1.start();

        thread2.start();

        thread1.join();

        thread2.join();

        assertEquals(2, successCount[0]);
    }
}