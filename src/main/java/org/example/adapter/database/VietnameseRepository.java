package org.example.adapter.database;

import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.VietnameseInvoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VietnameseRepository implements VietnameseInvoiceRepository {

    private Connection connection;

    public VietnameseRepository() {
        // Connect to MySQL database
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/invoice",
                    "root",
                    "password"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createInvoice(VietnameseInvoice invoice) {
        String sql = "INSERT INTO VietnameseInvoice (customerId, fullName, invoiceDate, quantity, price, customerType, quota) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(invoice.getCustomerId()));
            statement.setString(2, invoice.getFullName());
            statement.setDate(3, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            statement.setInt(4, invoice.getQuantity());
            statement.setDouble(5, invoice.getPrice());
            statement.setString(6, invoice.getCustomerType());
            statement.setInt(7, invoice.getQuota());
            System.out.println(statement);
            statement.executeUpdate();
            return true; 
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteInvoice(VietnameseInvoice invoice) {
        String sql = "DELETE FROM VietnameseInvoice WHERE customerId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(invoice.getCustomerId()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateInvoice(VietnameseInvoice invoice) {
        String sql = "UPDATE VietnameseInvoice SET fullName = ?, invoiceDate = ?, quantity = ?, price = ?, customerType = ?, quota = ? WHERE customerId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, invoice.getFullName());
            statement.setDate(2, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            statement.setInt(3, invoice.getQuantity());
            statement.setDouble(4, invoice.getPrice());
            statement.setString(5, invoice.getCustomerType());
            statement.setInt(6, invoice.getQuota());
            statement.setString(7, String.valueOf(invoice.getCustomerId()));
            statement.executeUpdate();
            System.out.println(statement.toString());
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public int getTotalAmountOfInvoice() {
        return 0;
    }

    @Override
    public int getTotalAmountOfInvoiceByMonth(int month) {
        String sql = "SELECT SUM(quantity * price) FROM VietnameseInvoice WHERE MONTH(invoiceDate) = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<VietnameseInvoice> findInvoices(String query) {
        String sql = "SELECT * FROM VietnameseInvoice WHERE fullName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            List<VietnameseInvoice> invoices = new java.util.ArrayList<>();
            while (resultSet.next()) {
                VietnameseInvoice invoice = new VietnameseInvoice();
                invoice.setCustomerId(resultSet.getInt("customerId"));
                invoice.setFullName(resultSet.getString("fullName"));
                invoice.setInvoiceDate(resultSet.getDate("invoiceDate").toLocalDate());
                invoice.setQuantity(resultSet.getInt("quantity"));
                invoice.setPrice(resultSet.getInt("price"));
                invoice.setCustomerType(resultSet.getString("customerType"));
                invoice.setQuota(resultSet.getInt("quota"));
                invoices.add(invoice);
            }
            return invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VietnameseInvoice> getInvoices() {
        String sql = "SELECT * FROM VietnameseInvoice";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<VietnameseInvoice> invoices = new java.util.ArrayList<>();
            while (resultSet.next()) {
                VietnameseInvoice invoice = new VietnameseInvoice();
                invoice.setCustomerId(resultSet.getInt("customerId"));
                invoice.setFullName(resultSet.getString("fullName"));
                invoice.setInvoiceDate(resultSet.getDate("invoiceDate").toLocalDate());
                invoice.setQuantity(resultSet.getInt("quantity"));
                invoice.setPrice(resultSet.getInt("price"));
                invoice.setCustomerType(resultSet.getString("customerType"));
                invoice.setQuota(resultSet.getInt("quota"));
                invoices.add(invoice);
            }
            return invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
