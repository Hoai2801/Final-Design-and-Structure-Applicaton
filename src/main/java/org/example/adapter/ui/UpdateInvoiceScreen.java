package org.example.adapter.ui;

import org.example.adapter.controller.InvoiceController;
import org.example.adapter.presenter.UpdateScreenPresenter;
import org.example.adapter.presenter.HomePresenter;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class UpdateInvoiceScreen extends JFrame {
    private UpdateScreenPresenter presenter;
    private HomePresenter homePresenter;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextField limitField; // Only for Vietnamese Customer
    private JTextField customerTypeField; // Only for Vietnamese Customer
    private JTextField nationalityField; // Only for Foreign Customer
    private JComboBox<String> customerTypeCombo;
    private JButton updateButton;
    private int invoiceId; // ID of the invoice being updated

    public UpdateInvoiceScreen() {
        setTitle("Update Invoice");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel customerTypeLabel = new JLabel("Customer Type:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(customerTypeLabel, gbc);

        customerTypeCombo = new JComboBox<>(new String[]{"Vietnamese", "Foreign"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(customerTypeCombo, gbc);

        JLabel customerIdLabel = new JLabel("Customer ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(customerIdLabel, gbc);

        customerIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(customerIdField, gbc);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(nameField, gbc);

        JLabel dateLabel = new JLabel("Invoice Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dateLabel, gbc);

        dateField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(dateField, gbc);

        JLabel unitPriceLabel = new JLabel("Unit Price:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(unitPriceLabel, gbc);

        unitPriceField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(unitPriceField, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(quantityLabel, gbc);

        quantityField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(quantityField, gbc);

        JLabel limitLabel = new JLabel("Consumption Limit (Vietnamese only):");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(limitLabel, gbc);

        limitField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(limitField, gbc);

        JLabel customerTypeSpecificLabel = new JLabel("Customer Type (Vietnamese only):");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(customerTypeSpecificLabel, gbc);

        customerTypeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(customerTypeField, gbc);

        JLabel nationalityLabel = new JLabel("Nationality (Foreign only):");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(nationalityLabel, gbc);

        nationalityField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(nationalityField, gbc);

        updateButton = new JButton("Update Invoice");
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(updateButton, gbc);

        // Hide fields specific to each customer type initially
//        toggleFieldsVisibility(false);

//        customerTypeCombo.addActionListener(e -> toggleFieldsVisibility("Vietnamese".equals(customerTypeCombo.getSelectedItem())));

        // Add ActionListener to the update button
        updateButton.addActionListener(e -> updateInvoice());
        
    }

//    private void toggleFieldsVisibility(boolean isVietnamese) {
//        limitField.setVisible(isVietnamese);
//        customerTypeField.setVisible(isVietnamese);
//        nationalityField.setVisible(!isVietnamese);
//        revalidate();
//        repaint();
//    }

    public void setPresenter(UpdateScreenPresenter presenter) {
        this.presenter = presenter;
    }

    public void setHomePresenter(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    public void open() {
        setVisible(true);
    }

    private void fillForm(RequestModel invoiceData) {
        customerTypeCombo.setSelectedItem(invoiceData.getCustomerType());
        customerIdField.setText(String.valueOf(invoiceData.getCustomerId()));
        nameField.setText(invoiceData.getFullName());
        dateField.setText(invoiceData.getInvoiceDate().toString());
        unitPriceField.setText(String.valueOf(invoiceData.getPrice()));
        quantityField.setText(String.valueOf(invoiceData.getQuantity()));
        if ("Vietnamese".equals(invoiceData.getCustomerType())) {
            limitField.setText(String.valueOf(invoiceData.getQuota()));
            customerTypeField.setText(invoiceData.getCustomerType());
        } else {
            nationalityField.setText(invoiceData.getNationality());
        }
//        toggleFieldsVisibility("Vietnamese".equals(invoiceData.getCustomerType()));
    }

    private void updateInvoice() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            String customerType = (String) customerTypeCombo.getSelectedItem();

            RequestModel requestModel;
            if ("Vietnamese".equals(customerType)) {
                double consumptionLimit = Double.parseDouble(limitField.getText());
                String customerCategory = customerTypeField.getText();
                requestModel = new RequestModel(invoiceId, customerId, name, customerCategory, "Vietnam", date, quantity, unitPrice, consumptionLimit);
            } else {
                String nationality = nationalityField.getText();
                requestModel = new RequestModel(invoiceId, customerId, name, "none", nationality, date, quantity, unitPrice, 0);
            }
            presenter.updateInvoice(requestModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input invalid", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showNotification(ResponseModel message) {
        if (message.isSuccess()) {
            JOptionPane.showMessageDialog(null, message.getMessage(), "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            presenter.updateHomeScreen();
        } else {
            JOptionPane.showMessageDialog(null, message.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fetchInvoice(InvoiceDTO invoice) {
        invoiceId = invoice.getInvoiceId();
        customerTypeCombo.setSelectedItem(invoice.getCustomerType());
        customerTypeCombo.setEnabled(false);
        customerIdField.setText(String.valueOf(invoice.getCustomerId()));
        nameField.setText(invoice.getFullName());
        dateField.setText(invoice.getInvoiceDate().toString());
        unitPriceField.setText(String.valueOf(invoice.getPrice()));
        quantityField.setText(String.valueOf(invoice.getQuantity()));
        System.out.println(invoice.getNationality());
        if ("Vietnam".equals(invoice.getNationality())) {
            limitField.setText(String.valueOf(invoice.getQuota()));
            customerTypeField.setText(invoice.getCustomerType());
            limitField.setVisible(true);
            customerTypeField.setVisible(true);
            nationalityField.setVisible(false);
            
        } else {
            nationalityField.setText(invoice.getNationality());
            nationalityField.setVisible(true);
            limitField.setVisible(false);
            customerTypeField.setVisible(false);
        }
//        toggleFieldsVisibility("Vietnamese".equals(invoice.getCustomerType()));
    }
}
