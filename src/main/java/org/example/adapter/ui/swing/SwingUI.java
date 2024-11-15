//package org.example.adapter.ui.swing;
//
//import org.example.adapter.controller.InvoiceController;
//import org.example.adapter.observer.Observer;
//import org.example.adapter.ui.model.InvoicesModel;
//import org.example.domain.entities.dtos.InvoiceDTO;
//import org.example.domain.entities.models.ListInvoiceResponseModel;
//import org.example.domain.entities.models.RequestModel;
//import org.example.domain.entities.models.ResponseModel;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import java.awt.*;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Objects;
//
//
//public class SwingUI extends JFrame implements Observer {
//    private InvoiceController invoiceController;
//    private DefaultTableModel tableModel;
//
//    // Form components
//    JComboBox<String> cbCustomer = new JComboBox<>(new String[]{"Choose customer", "Vietnamese", "Foreign"});
//    NumberTextField txtCustomerId = new NumberTextField();
//    LetterOnlyTextField txtFullName = new LetterOnlyTextField();
//    NumberTextField txtQuantity = new NumberTextField();
//    NumberTextField txtPrice = new NumberTextField();
//    NumberTextField txtQuota = new NumberTextField();
//    JComboBox<String> cbCustomerType = new JComboBox<>(new String[]{"Living", "Business", "Production"});
//    LetterOnlyTextField txtNationality = new LetterOnlyTextField();
//    JButton btnAddInvoice = new JButton("Add Invoice");
//    JButton btnUpdateInvoice = new JButton("Update Invoice");
//    JButton btnDeleteInvoice = new JButton("Delete Invoice");
//
//
//    public SwingUI() {
//    }
//
//    public void setInvoiceController(InvoiceController invoiceController) {
//        this.invoiceController = invoiceController;
//    }
//
//    public void initComponents() {
//        setTitle("Invoice Management");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        setBackground(new Color(240, 240, 240));
//
//        // Table to display invoices
//        String[] columnNames = {"Customer ID", "Full Name", "Invoice Date", "Nationality", "Customer Type", "Quantity", "Price", "Quota", "Total"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//
////        invoiceController.generateReport();
//
//        // Custom JTable
//        JTable invoiceTable = new JTable(tableModel) {
//            @Override
//            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//                Component c = super.prepareRenderer(renderer, row, column);
//                // Alternate row colors
//                if (!isRowSelected(row)) {
//                    c.setBackground(row % 2 == 0 ? new Color(230, 230, 255) : Color.WHITE);
//                } else {
//                    c.setBackground(new Color(175, 205, 250));
//                }
//                return c;
//            }
//        };
//
//        // Table appearance customization
//        invoiceTable.setRowHeight(30);
//        invoiceTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        invoiceTable.setGridColor(Color.LIGHT_GRAY);
//        invoiceTable.setShowGrid(true);
//        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        // Custom table header
//        JTableHeader tableHeader = invoiceTable.getTableHeader();
//        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
//        tableHeader.setBackground(new Color(70, 130, 180));
//        tableHeader.setForeground(Color.WHITE);
//        tableHeader.setPreferredSize(new Dimension(100, 40));
//
//        JScrollPane scrollPane = new JScrollPane(invoiceTable);
//        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Create form panel
//        JPanel formPanel = new JPanel();
//        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        GroupLayout layout = new GroupLayout(formPanel);
//        formPanel.setLayout(layout);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        // Labels
//        JLabel cbCustomerTypeLabel = new JLabel("Customer Type:");
//        JLabel lblCustomerId = new JLabel("Customer ID:");
//        JLabel lblFullName = new JLabel("Full Name:");
//        JLabel lblQuantity = new JLabel("Quantity (KW):");
//        JLabel lblPrice = new JLabel("Price per KW:");
//        JLabel lblQuota = new JLabel("Quota (Vietnamese only):");
//        JLabel lblCustomerType = new JLabel("Customer Type:");
//        JLabel lblNationality = new JLabel("Nationality (Foreign only):");
//
//        ListSelectionModel selectionModel = invoiceTable.getSelectionModel();
//        selectionModel.addListSelectionListener(event -> {
//            if (!event.getValueIsAdjusting()) {
//                int selectedRow = invoiceTable.getSelectedRow();
//                if (selectedRow != -1) {
//                    // Get data from the selected row
//                    String customerId = tableModel.getValueAt(selectedRow, 0).toString();
//                    String fullName = tableModel.getValueAt(selectedRow, 1).toString();
//                    String quantity = tableModel.getValueAt(selectedRow, 5).toString();
//                    String price = tableModel.getValueAt(selectedRow, 6).toString();
//                    String customerType = tableModel.getValueAt(selectedRow, 4).toString();
//                    String nationality = tableModel.getValueAt(selectedRow, 3).toString();
//                    String quota = tableModel.getValueAt(selectedRow, 7).toString();
//                    // Populate form fields
//                    txtCustomerId.setText(customerId);
//                    txtFullName.setText(fullName);
//                    txtQuantity.setText(quantity);
//                    txtPrice.setText(price);
//                    cbCustomerType.setSelectedItem(customerType);
//                    txtNationality.setText(nationality);
//                    cbCustomer.setSelectedItem(nationality.equals("Vietnam") ? "Vietnamese" : "Foreign");
//                    txtQuota.setText(quota);
//
//                    cbCustomer.setEnabled(false);
//                    txtCustomerId.setEditable(false);
//                }
//            }
//        });
//
//        cbCustomer.addActionListener(e -> {
//            if (cbCustomer.getSelectedItem().equals("Vietnamese")) {
//                txtNationality.setVisible(false);
//                lblNationality.setVisible(false);
//                txtQuota.setVisible(true); // Assuming quota is needed for Vietnamese
//                lblQuota.setVisible(true);
//            } else {
//                txtNationality.setVisible(true);
//                lblNationality.setVisible(true);
//                txtQuota.setVisible(false); // Assuming quota is not needed for Foreign
//                lblQuota.setVisible(false);
//            }
//        });
//
//        // Button to add invoice
//        btnAddInvoice.setFont(new Font("Arial", Font.BOLD, 14));
//        btnAddInvoice.setBackground(new Color(70, 130, 180));
//        btnAddInvoice.setForeground(Color.WHITE);
//
//        // Button to update invoice
//        btnUpdateInvoice.setFont(new Font("Arial", Font.BOLD, 14));
//        btnUpdateInvoice.setBackground(new Color(70, 130, 180));
//        btnUpdateInvoice.setForeground(Color.WHITE);
//
//        // Button to delete invoice
//        btnDeleteInvoice.setFont(new Font("Arial", Font.BOLD, 14));
//        btnDeleteInvoice.setBackground(new Color(70, 130, 180));
//        btnDeleteInvoice.setForeground(Color.WHITE);
//
//        JButton resetBtn = new JButton("Reset Invoice");
//        resetBtn.setFont(new Font("Arial", Font.BOLD, 14));
//        resetBtn.setBackground(new Color(70, 130, 180));
//        resetBtn.setForeground(Color.WHITE);
//
//        // GroupLayout horizontal grouping
//        layout.setHorizontalGroup(
//                layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                .addComponent(cbCustomerTypeLabel)
//                                .addComponent(lblCustomerId)
//                                .addComponent(lblFullName)
//                                .addComponent(lblQuantity)
//                                .addComponent(lblPrice)
//                                .addComponent(lblQuota)
//                                .addComponent(lblCustomerType)
//                                .addComponent(lblNationality))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                .addComponent(cbCustomer)
//                                .addComponent(txtCustomerId)
//                                .addComponent(txtFullName)
//                                .addComponent(txtQuantity)
//                                .addComponent(txtPrice)
//                                .addComponent(txtQuota)
//                                .addComponent(cbCustomerType)
//                                .addComponent(txtNationality))
//        );
//
//        // GroupLayout vertical grouping
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(cbCustomerTypeLabel))
//                        .addComponent(cbCustomer)
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblCustomerId)
//                                .addComponent(txtCustomerId))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblFullName)
//                                .addComponent(txtFullName))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblQuantity)
//                                .addComponent(txtQuantity))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblPrice)
//                                .addComponent(txtPrice))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblQuota)
//                                .addComponent(txtQuota))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblCustomerType)
//                                .addComponent(cbCustomerType))
//                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                .addComponent(lblNationality)
//                                .addComponent(txtNationality))
//        );
//
//        add(formPanel, BorderLayout.NORTH);
//
//        // Buttons panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(new Color(240, 240, 240));
//        buttonPanel.add(btnAddInvoice);
//        buttonPanel.add(btnUpdateInvoice);
//        buttonPanel.add(btnDeleteInvoice);
//        buttonPanel.add(resetBtn);
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Add invoice action
//        btnAddInvoice.addActionListener(e -> {
//            String fullName = txtFullName.getText();
//            int quantity = Integer.parseInt(txtQuantity.getText());
//            int price = Integer.parseInt(txtPrice.getText());
//
//            int customerId = Integer.parseInt(txtCustomerId.getText());
//            if (Objects.equals(cbCustomer.getSelectedItem(), "Vietnamese")) {
//                int quota = Integer.parseInt(txtQuota.getText());
//                RequestModel requestModel = new RequestModel(customerId, fullName, cbCustomerType.getSelectedItem().toString(), "Vietnam", LocalDate.now(), quantity, price, quota);
//                invoiceController.createInvoice(requestModel);
//            } else {
//                String nationality = txtNationality.getText();
//                RequestModel requestModel = new RequestModel(customerId, fullName, cbCustomerType.getSelectedItem().toString(), nationality, LocalDate.now(), quantity, price, 0);
//                invoiceController.createInvoice(requestModel);
//            }
//            resetForm();
//        });
//
//        // Update invoice action
//        btnUpdateInvoice.addActionListener(e -> {
//            int customerId = Integer.parseInt(txtCustomerId.getText());
//            String fullName = txtFullName.getText();
//            int quantity = Integer.parseInt(txtQuantity.getText());
//            int price = Integer.parseInt(txtPrice.getText());
//            String customerType = cbCustomerType.getSelectedItem().toString();
//            String nationality = txtNationality.getText();
//            int quota = Integer.parseInt(txtQuota.getText());
//            RequestModel requestModel = new RequestModel(customerId, fullName, customerType, nationality, LocalDate.now(), quantity, price, quota);
//            invoiceController.updateInvoice(requestModel);
//            resetForm();
//        });
//
//        // Delete invoice action
//        btnDeleteInvoice.addActionListener(e -> {
//            int customerId = Integer.parseInt(txtCustomerId.getText());
//            RequestModel requestModel = new RequestModel(customerId, null, null, txtNationality.getText(), null, 0, 0, 0);
//            invoiceController.deleteInvoice(requestModel);
//            resetForm();
//        });
//
//        resetBtn.addActionListener(e -> resetForm());
//
//        // Final frame settings
//        setSize(800, 500);
//        setVisible(true);
//    }
//
//    private void resetForm() {
//        cbCustomerType.setSelectedIndex(0); // Resets to the first item
//        cbCustomer.setSelectedIndex(1); // Resets to the first item
//
//        cbCustomer.setEditable(true);
//        cbCustomer.setEnabled(true);
//        txtCustomerId.setEditable(true);
//        txtCustomerId.setText("");
//        txtFullName.setText("");
//        txtQuantity.setText("");
//        txtPrice.setText("");
//        txtQuota.setText("");
//        txtNationality.setText("");
//
//    }
//
//
//    @Override
//    public void updateMessage(ResponseModel responseModel) {
//        JOptionPane.showMessageDialog(null, responseModel.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
//        invoiceController.generateReport();
//    }
//
//    @Override
//    public void updateError(ResponseModel error) {
//        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    @Override
//    public void generateReport(ListInvoiceResponseModel invoices) {
//        tableModel.setRowCount(0);
//        if (invoices.getInvoices().isEmpty()) {
//            btnUpdateInvoice.setEnabled(false);
//            btnDeleteInvoice.setEnabled(false);
//        } else {
//            btnUpdateInvoice.setEnabled(true);
//            btnDeleteInvoice.setEnabled(true);
//        }
//        for (InvoiceDTO invoice : invoices.getInvoices()) {
//            tableModel.addRow(new Object[]{invoice.getCustomerId(), invoice.getFullName(), invoice.getInvoiceDate(), invoice.getNationality(), invoice.getCustomerType(), invoice.getQuantity(), invoice.getPrice(), invoice.getQuota(), invoice.getTotal()});
//        }
//    }
//}
