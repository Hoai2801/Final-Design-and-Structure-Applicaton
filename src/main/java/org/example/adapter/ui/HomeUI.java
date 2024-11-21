package org.example.adapter.ui;

import org.example.adapter.controller.HomeController;
import org.example.domain.entities.dtos.InvoiceDTO;
import org.example.domain.entities.models.ResponseModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HomeUI extends JFrame implements Screen {
    private HomeController homeController;
    JLabel labelTotalInvoices;
    JLabel labelTotalInvoicesOfVietnamese;
    JLabel labelTotalInvoicesOfForeign;
    JScrollPane tableScrollPane;
    DefaultTableModel tableModel;
    JTable table;

    public HomeUI() {
        super("Invoice Management System");
    }

    public void init() {
        // Set layout and main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // For absolute positioning similar to AnchorPane
        mainPanel.setPreferredSize(new Dimension(1000, 600));

        // Title Text
        JLabel titleText = new JLabel("Chướng mắt");
        titleText.setFont(new Font("Arial", Font.BOLD, 23));
        titleText.setBounds(40, 10, 200, 30);
        mainPanel.add(titleText);

        // "Create" Button
        JButton createButton = new JButton("Create");
        createButton.setBounds(850, 14, 100, 33);
        createButton.setBackground(new Color(0, 159, 207));  // #009FCF color
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.addActionListener(e -> {
            homeController.openCreateScreen();
        });

        mainPanel.add(createButton);

        // search bar
        JTextField searchBar = new JTextField();
        searchBar.setBounds(40, 50, 300, 30);
        mainPanel.add(searchBar);

        // search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(350, 50, 100, 30);
        searchButton.setBackground(new Color(0, 159, 207));  // #009FCF color
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> {
            homeController.searchInvoice(searchBar.getText());
        });
        mainPanel.add(searchButton);

        // Label 1: "Tổng hóa đơn"
        labelTotalInvoices = new JLabel("Tổng hóa đơn:");
        labelTotalInvoices.setBounds(40, 90, 190, 60);
        labelTotalInvoices.setOpaque(true);
        labelTotalInvoices.setBackground(new Color(93, 74, 199));  // #5D4AC7 color
        labelTotalInvoices.setForeground(Color.WHITE);
        labelTotalInvoices.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalInvoices.setBorder(new EmptyBorder(10, 10, 10, 10));  // Label padding
        mainPanel.add(labelTotalInvoices);

//        homeController.getTotalInvoices();
        
        // Label 2: "Tong tien thang nay"
        labelTotalInvoicesOfVietnamese = new JLabel("Tổng hóa đơn khách Việt:");
        labelTotalInvoicesOfVietnamese.setBounds(250, 90, 260, 60);
        labelTotalInvoicesOfVietnamese.setOpaque(true);
        labelTotalInvoicesOfVietnamese.setBackground(new Color(69, 76, 104));  // #454C68 color
        labelTotalInvoicesOfVietnamese.setForeground(Color.WHITE);
        labelTotalInvoicesOfVietnamese.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalInvoicesOfVietnamese.setBorder(new EmptyBorder(10, 10, 10, 10));  // Label padding
        mainPanel.add(labelTotalInvoicesOfVietnamese);

        // Label 2: "Tong tien thang nay"
        labelTotalInvoicesOfForeign = new JLabel("Tổng hóa đơn khách ngoại:");
        labelTotalInvoicesOfForeign.setBounds(530, 90, 260, 60);
        labelTotalInvoicesOfForeign.setOpaque(true);
        labelTotalInvoicesOfForeign.setBackground(new Color(29, 63, 104));  // #454C68 color
        labelTotalInvoicesOfForeign.setForeground(Color.WHITE);
        labelTotalInvoicesOfForeign.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalInvoicesOfForeign.setBorder(new EmptyBorder(10, 10, 10, 10));  // Label padding
        mainPanel.add(labelTotalInvoicesOfForeign);

//        homeController.getTotalInvoicesOfCustomerType();

        // Table
        String[] columnNames = {"Invoice ID","Customer ID", "Full Name", "Invoice Date", "Nationality", "Customer Type", "Quantity", "Price", "Quota", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0); // Initialize tableModel with column names
        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(39, 160, 930, 245);
        
        mainPanel.add(tableScrollPane);

//        homeController.getListInvoices();

        // Add right-click context menu
        JPopupMenu popupMenu = new JPopupMenu();
//        JMenuItem viewItem = new JMenuItem("View Invoice");
        JMenuItem editItem = new JMenuItem("Edit Invoice");
        JMenuItem deleteItem = new JMenuItem("Delete Invoice");

        // Add action listeners for each menu item
//        viewItem.addActionListener(e -> viewSelectedInvoice());
        editItem.addActionListener(e -> editSelectedInvoice());
        deleteItem.addActionListener(e -> deleteSelectedInvoice());

//        popupMenu.add(viewItem);
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        // Add mouse listener for the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != -1) {
                    table.setRowSelectionInterval(row, row); // Select the row
                    if (e.isPopupTrigger()) { // Check for right-click
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) { // Ensure popup also shows on mouse release
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });

        homeController.initHomeScreen();
        
        JButton chartBtn = new JButton("Chart");
        chartBtn.setBounds(39, 420, 100, 40);
        chartBtn.addActionListener(e -> showChart());
        this.add(chartBtn);

        // Add main panel to frame
        this.add(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void showChart() {
        homeController.openChartScreen();
    }
    
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    private void editSelectedInvoice() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Handle edit action
            int customerId = Integer.parseInt(String.valueOf(tableModel.getValueAt(selectedRow, 0)));
            String type = tableModel.getValueAt(selectedRow, 4).equals("Vietnam") ? "Vietnam" : "Foreign";
//            JOptionPane.showMessageDialog(this, "Editing invoice for customer ID: " + customerId);
            homeController.openUpdateScreen(customerId, type);
        }
    }

    private void deleteSelectedInvoice() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Handle delete action
            int customerId = (int) tableModel.getValueAt(selectedRow, 0);
            String type = tableModel.getValueAt(selectedRow, 4).equals("Vietnam") ? "Vietnam" : "Foreign";
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete invoice for customer ID: " + customerId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                homeController.deleteInvoice(customerId, type);
            }
        }
    }

    public void showTotalInvoices(int i) {
        labelTotalInvoices.setText("Tổng hóa đơn: " + i);
    }

    public void showTotalInvoicesVietnamCustomer(int i) {
        labelTotalInvoicesOfVietnamese.setText("Tổng hóa đơn khách Việt: " + i);
    }

    public void showTotalInvoicesForeignCustomer(int i) {
        labelTotalInvoicesOfForeign.setText("Tổng hóa đơn khách ngoại: " + i);
    }

    public void showListInvoices(List<InvoiceDTO> invoices) {
        tableModel.setRowCount(0); // Clear existing rows
        for (InvoiceDTO invoice : invoices) {
            tableModel.addRow(new Object[] {
                    invoice.getInvoiceId(),
                    invoice.getCustomerId(),
                    invoice.getFullName(),
                    invoice.getInvoiceDate(),
                    invoice.getNationality(),
                    invoice.getCustomerType(),
                    invoice.getQuantity(),
                    invoice.getPrice(),
                    invoice.getQuota(),
                    invoice.getTotal()
            });
        }
    }

    public void updateHomeScreen() {
        homeController.initHomeScreen();
    }

    @Override
    public void notify(ResponseModel responseModel) {
        if (responseModel.isSuccess()) {
            updateHomeScreen();
        }
    }
}
