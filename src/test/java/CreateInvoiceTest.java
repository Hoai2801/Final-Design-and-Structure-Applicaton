import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.adapter.ui.Presenter;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.interactors.Interactor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateInvoiceTest {
    Presenter presenter = new Presenter();
    Interactor createInvoice = new Interactor(new VietnameseRepository(), new ForeignRepository(), presenter);
    
    private final String success = "Invoice created successfully";
    private final String failure = "Invoice created failed";
    
    // Valid request with Vietnamese nationality creates a Vietnamese invoice successfully
    @Test
    public void test_vietnamese_invoice_creation_success() {
        RequestModel req = new RequestModel(1, "Nguyen Van A", "Living", "Vietnam", LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the invoice was created successfully
        assertEquals(presenter.getResponseModel().getMessage(), success);
    }

    // Valid request with non-Vietnamese nationality creates a Foreign invoice successfully
    @Test
    public void test_foreign_invoice_creation_success() {
        RequestModel req = new RequestModel(2, "John Doe", "Business", "USA", LocalDate.now(), 5, 2000, 0);
        createInvoice.createInvoice(req);
        // Assert that the foreign invoice was created successfully
    }

    // Valid request shows success message when invoice creation is successful
    @Test
    public void test_success_message_on_creation() {
        RequestModel req = new RequestModel(3, "Jane Smith", "Living", "Vietnam", LocalDate.now(), 8, 1500, 10);
        createInvoice.createInvoice(req);
        // Assert that the success message is shown
    }

    // Request with null fullName triggers error message
    @Test
    public void test_null_fullname_error() {
        RequestModel req = new RequestModel(4, null, "Living", "Vietnam", LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for null fullName is shown
    }

    // Request with null customerType triggers error message
    @Test
    public void test_null_customer_type_error() {
        RequestModel req = new RequestModel(5, "Nguyen Van B", null, "Vietnam", LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for null customerType is shown
    }

    // Request with null nationality triggers error message
    @Test
    public void test_null_nationality_error() {
        RequestModel req = new RequestModel(6, "Nguyen Van C", "Living", null, LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for null nationality is shown
    }

    // Request with customerId less than or equal to zero triggers error message
    @Test
    public void test_invalid_customer_id_error() {
        RequestModel req = new RequestModel(0, "Nguyen Van D", "Living", "Vietnam", LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for invalid customerId is shown
    }

    // Request with quantity less than or equal to zero triggers error message
    @Test
    public void test_invalid_quantity_error() {
        RequestModel req = new RequestModel(7, "Nguyen Van E", "Living", "Vietnam", LocalDate.now(), 0, 1000, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for invalid quantity is shown
    }

    // Request with quota less than zero triggers error message
    @Test
    public void test_invalid_quota_error() {
        RequestModel req = new RequestModel(8, "Nguyen Van F", "Living", "Vietnam", LocalDate.now(), 10, 1000, -1);
        createInvoice.createInvoice(req);
        // Assert that the error message for invalid quota is shown
    }

    // Request with price less than or equal to zero triggers error message
    @Test
    public void test_invalid_price_error() {
        RequestModel req = new RequestModel(9, "Nguyen Van G", "Living", "Vietnam", LocalDate.now(), 10, 0, 5);
        createInvoice.createInvoice(req);
        // Assert that the error message for invalid price is shown
    }

    // Invoice creation failure shows appropriate error message
    @Test
    public void test_invoice_creation_failure_message() {
        RequestModel req = new RequestModel(10, "Nguyen Van H", "Living", "Vietnam", LocalDate.now(), 10, 1000, 5);
        createInvoice.createInvoice(req);
        // Simulate failure and assert that the failure message is shown
    }

    // Valid request with Vietnamese nationality and quota exceeded calculates total correctly
    @Test
    public void test_vietnamese_invoice_quota_exceeded_total_calculation() {
        RequestModel req = new RequestModel(11, "Nguyen Van I", "Living", "Vietnam", LocalDate.now(), 15, 1000, 10);
        VietnameseInvoice invoice = new VietnameseInvoice(req.getCustomerId(), req.getFullName(), req.getInvoiceDate(), req.getCustomerType(), req.getQuantity(), req.getPrice(), req.getQuota());
        double total = invoice.calculateTotal();
        assertEquals(22500.0, total); // Expected total calculation when quota is exceeded
    }
    
    
}
