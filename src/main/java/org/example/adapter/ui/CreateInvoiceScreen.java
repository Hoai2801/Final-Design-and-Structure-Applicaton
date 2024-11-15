package org.example.adapter.ui;

import org.example.adapter.controller.InvoiceController;
import org.example.adapter.presenter.CreateScreenPresenter;
import org.example.adapter.presenter.HomePresenter;
import org.example.domain.entities.models.RequestModel;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CreateInvoiceScreen extends JFrame {
    private CreateScreenPresenter presenter;
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

        saveButton = new JButton("Save Invoice");
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(saveButton, gbc);

        // Hide fields specific to each customer type initially
        limitField.setVisible(false);
        customerTypeField.setVisible(false);
        nationalityField.setVisible(false);
        limitLabel.setVisible(false);
        customerTypeSpecificLabel.setVisible(false);
        nationalityLabel.setVisible(false);

        // Add ActionListener to the JComboBox to switch fields based on customer type
        customerTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) customerTypeCombo.getSelectedItem();
                if ("Vietnamese".equals(selectedType)) {
                    limitField.setVisible(true);
                    customerTypeField.setVisible(true);
                    nationalityField.setVisible(false);
                    limitLabel.setVisible(true);
                    customerTypeSpecificLabel.setVisible(true);
                    nationalityLabel.setVisible(false);
                } else {
                    limitField.setVisible(false);
                    customerTypeField.setVisible(false);
                    nationalityField.setVisible(true);
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
            String name = nameField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            String customerType = (String) customerTypeCombo.getSelectedItem();

            if ("Vietnamese".equals(customerType)) {
                double consumptionLimit = Double.parseDouble(limitField.getText());
                String customerCategory = customerTypeField.getText();
                
                RequestModel requestModel = new RequestModel(0, customerId, name, customerCategory, "Vietnam", date, quantity, unitPrice, consumptionLimit);
                presenter.createInvoice(requestModel);
            } else {
                String nationality = nationalityField.getText();
                
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
    
    public void setHomePresenter(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    public void open() {
        setVisible(true);
    }
    
    public void clearForm() {
        customerTypeCombo.setSelectedIndex(0);
        customerIdField.setText("");
        nameField.setText("");
        dateField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
        limitField.setText("");
        customerTypeField.setText("");
        nationalityField.setText("");
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
}
