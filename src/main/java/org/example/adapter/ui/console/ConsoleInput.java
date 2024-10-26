package org.example.adapter.ui.console;

import org.example.adapter.controller.InvoiceController;
import org.example.domain.entities.models.RequestModel;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleInput {
    private final InvoiceController controller;

    public ConsoleInput(InvoiceController controller) {
        this.controller = controller;
    }

    public void start() {
        showMenu();
    }

    public void showMenu() {
        System.out.println("1. Create invoice");
        System.out.println("2. Update invoice");
        System.out.println("3. Delete invoice");
        System.out.println("4. Generate report");
        System.out.println("5. Count total amount of invoices");
        getInput();
    }

    public void getInput() {
        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        RequestModel requestModel = new RequestModel();

        switch (choice) {
            case 1:
            case 2:
                System.out.println("Enter customer id: ");
                requestModel.setCustomerId(scanner.nextInt());

                // Capture the full name with spaces
                System.out.println("Enter customer name: ");
                scanner.nextLine(); // Consume the newline left by nextInt()
                requestModel.setFullName(scanner.nextLine());
                requestModel.setInvoiceDate(LocalDate.now());

                System.out.println("Vietnamese (1) or foreign (2)?");
                int nationalityChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()

                if (nationalityChoice == 1) {
                    System.out.println("Enter customer type: ");
                    requestModel.setCustomerType(scanner.nextLine());
                    System.out.println("Enter quota: ");
                    requestModel.setQuota(scanner.nextInt());
                    scanner.nextLine(); // Consume the newline after nextInt()
                    requestModel.setNationality("Vietnam");
                } else {
                    System.out.println("Enter nationality: ");
                    requestModel.setNationality(scanner.nextLine());
                }

                System.out.println("Enter quantity: ");
                requestModel.setQuantity(scanner.nextInt());
                System.out.println("Enter price: ");
                requestModel.setPrice(scanner.nextInt());

                if (choice == 1) {
                    controller.createInvoice(requestModel);
                } else {
                    controller.updateInvoice(requestModel);
                }
                break;

            case 3:
                System.out.println("Enter customer id: ");
                requestModel.setCustomerId(scanner.nextInt());
                System.out.println("Vietnamese (1) or foreign (2)?");
                scanner.nextLine(); // Consume the newline left by nextInt()
                nationalityChoice = scanner.nextInt();
                requestModel.setNationality(nationalityChoice == 1 ? "Vietnam" : "Foreign");
                controller.deleteInvoice(requestModel);
                break;

            case 4:
                controller.generateReport();
                break;
            case 5:
                System.out.println("Type of invoice: ");
                System.out.println("1. Vietnamese");
                System.out.println("2. Foreign");
                System.out.println("Enter your choice: ");
                int type = scanner.nextInt();
                controller.getTotalAmountOfInvoice(type);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}
