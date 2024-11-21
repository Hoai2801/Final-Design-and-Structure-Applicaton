package org.example.adapter.ui;

import org.example.adapter.controller.UpdateController;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class UpdateInvoiceScreen extends JFrame implements Screen {
    private UpdateController controller;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextField limitField; // Only for Vietnamese Customer
    private JComboBox<String> customerTypeCombo; // Only for Vietnamese Customer
    private JComboBox<String> nationalityCombo; // Only for Foreign Customer
    private JComboBox<String> invoiceTypeCombo;
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

        invoiceTypeCombo = new JComboBox<>(new String[]{"Vietnamese", "Foreign"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(invoiceTypeCombo, gbc);

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

        customerTypeCombo = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(customerTypeCombo, gbc);

        JLabel nationalityLabel = new JLabel("Nationality (Foreign only):");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(nationalityLabel, gbc);

        nationalityCombo = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(nationalityCombo, gbc);

        updateButton = new JButton("Update Invoice");
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(updateButton, gbc);

        // Add ActionListener to the update button
        updateButton.addActionListener(e -> updateInvoice());
        
    }


    public void open(int invoiceId, String type) {
        setVisible(true);
        controller.loadDateForView(invoiceId, type);
    }

    private void updateInvoice() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            String customerType = (String) invoiceTypeCombo.getSelectedItem();

            RequestModel requestModel;
            if ("Vietnamese".equals(customerType)) {
                double consumptionLimit = Double.parseDouble(limitField.getText());
                String customerCategory = customerTypeCombo.getSelectedItem().toString();
                requestModel = new RequestModel(invoiceId, customerId, name, customerCategory, "Vietnam", date, quantity, unitPrice, consumptionLimit);
            } else {
                String nationality = nationalityCombo.getSelectedItem().toString();
                requestModel = new RequestModel(invoiceId, customerId, name, "none", nationality, date, quantity, unitPrice, 0);
            }
            controller.updateInvoice(requestModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input invalid", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showNotification(ResponseModel message) {
        if (message.isSuccess()) {
            JOptionPane.showMessageDialog(null, message.getMessage(), "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, message.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fetchInvoice(InvoiceDTO invoice) {
        invoiceId = invoice.getInvoiceId();
        invoiceTypeCombo.setSelectedItem(invoice.getCustomerType());
        invoiceTypeCombo.setEnabled(false);
        customerIdField.setText(String.valueOf(invoice.getCustomerId()));
        nameField.setText(invoice.getFullName());
        dateField.setText(invoice.getInvoiceDate().toString());
        limitField.setText(String.valueOf(invoice.getQuota()));
        unitPriceField.setText(String.valueOf(invoice.getPrice()));
        quantityField.setText(String.valueOf(invoice.getQuantity()));
        System.out.println(invoice.getNationality());
        if ("Vietnam".equals(invoice.getNationality())) {
            invoiceTypeCombo.setSelectedIndex(0);
            limitField.setText(String.valueOf(invoice.getQuota()));
            customerTypeCombo.setSelectedItem(invoice.getCustomerType());
            limitField.setVisible(true);
            customerTypeCombo.setVisible(true);
            nationalityCombo.setVisible(false);
        } else {
            invoiceTypeCombo.setSelectedIndex(1);
            nationalityCombo.setSelectedItem(invoice.getNationality());
            nationalityCombo.setVisible(true);
            limitField.setVisible(false);
            customerTypeCombo.setVisible(false);
        }
    }

    public void setCustomerType(List<String> customerTypes) {
        customerTypeCombo.removeAllItems();
        for (String customerType : customerTypes) {
            customerTypeCombo.addItem(customerType);
        }
    }

    public void setNationality(List<String> nationality) {
        nationalityCombo.removeAllItems();
        for (String national : nationality) {
            nationalityCombo.addItem(national);
        }
    }

    public void setController(UpdateController updateController) {
        this.controller = updateController;
    }

    @Override
    public void notify(ResponseModel message) {
        showNotification(message);
    }
}
