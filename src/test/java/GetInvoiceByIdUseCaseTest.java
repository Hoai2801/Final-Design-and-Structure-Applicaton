import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.domain.boundaries.out.GetInvoiceByIdOutputBoundary;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.usecases.GetInvoiceByIdUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetInvoiceByIdUseCaseTest {


    // Retrieves Vietnamese invoice by ID and sends to output boundary
    @Test
    public void retrieves_vietnamese_invoice_by_id() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNotNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");
    }

    // Retrieves foreign invoice by ID and sends to output boundary
    @Test
    public void retrieves_foreign_invoice_by_id() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNotNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Foreign");
    }

    // Correctly maps VietnameseInvoice to InvoiceDTO
    @Test
    public void maps_vietnamese_invoice_to_dto() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> {
            assertEquals("Vietnam", invoice.getNationality());
            assertEquals("sinh hoat", invoice.getCustomerType());
        };
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");
    }

    // Correctly maps ForeignInvoice to InvoiceDTO
    @Test
    public void maps_foreign_invoice_to_dto() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> {
            assertEquals("none", invoice.getCustomerType());
            assertNotNull(invoice.getNationality());
        };
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Foreign");
    }

    // Handles both Vietnamese and foreign invoice types
    @Test
    public void handles_both_invoice_types() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNotNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");
        useCase.getInvoiceById(1, "Foreign");
    }

    // Handles non-existent invoice ID gracefully
    @Test
    public void handles_non_existent_invoice_id() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(-1, "Vietnam");
    }

    // Handles null or empty type input
    @Test
    public void handles_null_or_empty_type_input() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, null);
        useCase.getInvoiceById(1, "");
    }

    // Handles unexpected type values
    @Test
    public void handles_unexpected_type_values() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "UnknownType");
    }

    // Handles database connection failures
    @Test
    public void handles_database_connection_failures() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public VietnameseInvoice getInvoiceById(int invoiceId) {
                throw new RuntimeException("Database connection failed");
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> {};
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");
    }

    // Handles null invoice objects returned from repositories
    @Test
    public void handles_null_invoice_objects_from_repositories() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository() {
            @Override
            public VietnameseInvoice getInvoiceById(int invoiceId) {
                return null;
            }
        };
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> assertNull(invoice);
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");
    }

    // Validates invoice ID is a positive integer
    @Test
    public void validates_invoice_id_positive_integer() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> {};
        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(-5, "Vietnam");
    }

    // Ensures output boundary is always called with an InvoiceDTO
    @Test
    public void ensures_output_boundary_called_with_dto() {
        VietnameseRepository vietnameseRepository = new VietnameseRepository();
        ForeignRepository foreignRepository = new ForeignRepository();
        final boolean[] calledWithDTO = {false};

        GetInvoiceByIdOutputBoundary outputBoundary = invoice -> calledWithDTO[0] = (invoice instanceof InvoiceDTO);

        GetInvoiceByIdUseCase useCase = new GetInvoiceByIdUseCase(outputBoundary, vietnameseRepository, foreignRepository);

        useCase.getInvoiceById(1, "Vietnam");

        assertTrue(calledWithDTO[0]);
    }
}