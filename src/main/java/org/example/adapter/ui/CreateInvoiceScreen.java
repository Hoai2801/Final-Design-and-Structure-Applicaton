package org.example.adapter.ui;

import org.example.adapter.presenter.CreateScreenPresenter;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class CreateInvoiceScreen extends JFrame {
    private CreateScreenPresenter presenter;
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextField limitField; // Only for Vietnamese Customer
    private JComboBox<String> customerTypeCombo; // Only for Vietnamese Customer
    private JComboBox<String> nationalityCombo; // Only for Foreign Customer
    private JComboBox<String> invoiceTypeCombo;
    private JButton saveButton;

    public CreateInvoiceScreen() {
        setTitle("Create Invoice");
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

        customerTypeCombo = new JComboBox<>(new String[]{"Customer A", "Customer B", "Customer C"});
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

        saveButton = new JButton("Save Invoice");
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(saveButton, gbc);

        // Hide fields specific to each customer type initially
        limitField.setVisible(false);
        customerTypeCombo.setVisible(false);
        nationalityCombo.setVisible(false);
        limitLabel.setVisible(false);
        customerTypeSpecificLabel.setVisible(false);
        nationalityLabel.setVisible(false);

        // Add ActionListener to the JComboBox to switch fields based on customer type
        invoiceTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) invoiceTypeCombo.getSelectedItem();
                if ("Vietnamese".equals(selectedType)) {
                    limitField.setVisible(true);
                    customerTypeCombo.setVisible(true);
                    nationalityCombo.setVisible(false);
                    limitLabel.setVisible(true);
                    customerTypeSpecificLabel.setVisible(true);
                    nationalityLabel.setVisible(false);
                } else {
                    limitField.setVisible(false);
                    customerTypeCombo.setVisible(false);
                    nationalityCombo.setVisible(true);
                    limitLabel.setVisible(false);
                    customerTypeSpecificLabel.setVisible(false);
                    nationalityLabel.setVisible(true);
                }
                revalidate();
                repaint();
            }
        });
        

        // Add ActionListener to the save button
        saveButton.addActionListener(e -> saveInvoice());
    }

    private void saveInvoice() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            if (customerId <= 0) {
                JOptionPane.showMessageDialog(null, "ID must be greater than 0", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            if (unitPrice <= 0) {
                JOptionPane.showMessageDialog(null, "price must be greater than 0", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double quantity = Double.parseDouble(quantityField.getText());
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "quantity must be greater than 0", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String customerType = (String) invoiceTypeCombo.getSelectedItem();

            if ("Vietnamese".equals(customerType)) {
                double consumptionLimit = Double.parseDouble(limitField.getText());
                String customerCategory = customerTypeCombo.getSelectedItem().toString();
                
                RequestModel requestModel = new RequestModel(0, customerId, name, customerCategory, "Vietnam", date, quantity, unitPrice, consumptionLimit);
                presenter.createInvoice(requestModel);
            } else {
                String nationality = nationalityCombo.getSelectedItem().toString();
                
                RequestModel requestModel = new RequestModel(0, customerId, name, "none", nationality, date, quantity, unitPrice, 0);
                presenter.createInvoice(requestModel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input invalid", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPresenter(CreateScreenPresenter presenter) {
        this.presenter = presenter;
    }

    public void open() {
        setVisible(true);
        presenter.getCustomerType();
        presenter.getNationality();
    }
    
    public void clearForm() {
        invoiceTypeCombo.setSelectedIndex(0);
        customerIdField.setText("");
        nameField.setText("");
        dateField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
        limitField.setText("");
        customerTypeCombo.setSelectedIndex(0);
        nationalityCombo.setSelectedIndex(0);
    }

    public void showNotification(ResponseModel message) {
        if (message.isSuccess()) {
            JOptionPane.showMessageDialog(null, message.getMessage(), "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            presenter.updateHomeScreen();
        } else {
            JOptionPane.showMessageDialog(null, message.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
}
