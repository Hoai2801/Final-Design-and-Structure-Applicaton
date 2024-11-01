import org.example.adapter.database.ForeignRepository;
import org.example.adapter.database.VietnameseRepository;
import org.example.adapter.ui.Presenter;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.interactors.Interactor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateInvoiceTest {
    Presenter presenter = new Presenter();
    Interactor updateInvoice = new Interactor(new VietnameseRepository(), new ForeignRepository(), presenter);

    // Update a Vietnamese invoice successfully when nationality is "Vietnam"
    @Test
    public void update_vietnamese_invoice_success() {
        RequestModel req = new RequestModel(1, "Nguyen Van A", "Regular", "Vietnam", LocalDate.now(), 10, 1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invoice updated successfully", presenter.getResponseModel().getMessage());
    }

    // Update a foreign invoice successfully when nationality is not "Vietnam"
    @Test
    public void update_foreign_invoice_success() {
        RequestModel req = new RequestModel(2, "John Doe", "Regular", "USA", LocalDate.now(), 5, 500, 0);
        updateInvoice.updateInvoice(req);
        assertEquals("Invoice updated successfully", presenter.getResponseModel().getMessage());
    }

    // Display success message when invoice update is successful
    @Test
    public void display_success_message_on_update() {
        RequestModel req = new RequestModel(3, "Jane Doe", "Premium", "Vietnam", LocalDate.now(), 15, 1500, 10);
        updateInvoice.updateInvoice(req);
        assertEquals("Invoice updated successfully", presenter.getResponseModel().getMessage());
    }

    // Display failure message when invoice update fails
    @Test
    public void display_failure_message_on_update_fail() {
        RequestModel req = new RequestModel(4, "Invalid User", "Regular", "Vietnam", LocalDate.now(), 0, 0, 0);
        updateInvoice.updateInvoice(req);
        assertEquals("Invalid price", presenter.getResponseModel().getMessage());
    }

    // Handle null nationality in RequestModel gracefully
    @Test
    public void handle_null_nationality_gracefully() {
        RequestModel req = new RequestModel(5, "Null Nationality", "Regular", null, LocalDate.now(), 10, 1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Nationality type cannot be empty", presenter.getResponseModel().getMessage());
    }

    // Handle empty fullName in RequestModel without exceptions
    @Test
    public void handle_empty_fullname_without_exceptions() {
        RequestModel req = new RequestModel(6, "", "Regular", "Vietnam", LocalDate.now(), 10, 1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invoice updated successfully", presenter.getResponseModel().getMessage());
    }

    // Handle negative customerId in RequestModel without exceptions
    @Test
    public void handle_negative_customerid_without_exceptions() {
        RequestModel req = new RequestModel(-1, "Negative ID", "Regular", "Vietnam", LocalDate.now(), 10, 1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invalid customer id", presenter.getResponseModel().getMessage());
    }

    // Handle zero or negative quantity in RequestModel without exceptions
    @Test
    public void handle_zero_negative_quantity_without_exceptions() {
        RequestModel req = new RequestModel(7, "Zero Quantity", "Regular", "Vietnam", LocalDate.now(), 0, 1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invalid quantity", presenter.getResponseModel().getMessage());
    }

    // Handle zero or negative price in RequestModel without exceptions
    @Test
    public void handle_zero_negative_price_without_exceptions() {
        RequestModel req = new RequestModel(8, "Zero Price", "Regular", "Vietnam", LocalDate.now(), 10, -1000, 5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invalid price", presenter.getResponseModel().getMessage());
    }

    // Handle negative quota in RequestModel without exceptions
    @Test
    public void handle_negative_quota_without_exceptions() {
        RequestModel req = new RequestModel(9, "Negative Quota", "Regular", "Vietnam", LocalDate.now(), 10, 1000, -5);
        updateInvoice.updateInvoice(req);
        assertEquals("Invalid quota", presenter.getResponseModel().getMessage());
    }
}
