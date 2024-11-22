package org.example.adapter.database;

import org.example.domain.boundaries.out.VietnameseInvoiceRepository;
import org.example.domain.entities.VietnameseInvoice;
import org.example.domain.entities.dtos.InvoiceDTO;

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
            statement.setDouble(4, invoice.getQuantity());
            statement.setDouble(5, invoice.getPrice());
            statement.setString(6, invoice.getCustomerType());
            statement.setDouble(7, invoice.getQuota());
            System.out.println(statement);
            statement.executeUpdate();
            return statement.getUpdateCount() > 0; 
        } catch (SQLException e) {
            return false;
        }
    }

//    @Override
//    public boolean deleteInvoice(VietnameseInvoice invoice) {
//        String sql = "DELETE FROM VietnameseInvoice WHERE customerId = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, String.valueOf(invoice.getCustomerId()));
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }

    @Override
    public boolean updateInvoice(VietnameseInvoice invoice) {
        String sql = "UPDATE VietnameseInvoice SET customerId = ?, fullName = ?, invoiceDate = ?, quantity = ?, price = ?, customerType = ?, quota = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(invoice.getCustomerId()));
            statement.setString(2, invoice.getFullName());
            statement.setDate(3, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            statement.setDouble(4, invoice.getQuantity());
            statement.setDouble(5, invoice.getPrice());
            statement.setString(6, invoice.getCustomerType());
            statement.setDouble(7, invoice.getQuota());
            statement.setInt(8, invoice.getInvoiceId());
            statement.executeUpdate();
            System.out.println(statement.toString());
            return true; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object[]> countInvoicesByMonth() {
        String sql = "SELECT DATE_FORMAT(invoiceDate, '%Y-%m') AS month, COUNT(*) AS count " +
                "FROM VietnameseInvoice " +
                "GROUP BY month";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Object[]> results = new java.util.ArrayList<>();
            while (resultSet.next()) {
                String month = resultSet.getString("month");
                int count = resultSet.getInt("count");
                results.add(new Object[]{month, count});
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch invoice counts by month", e);
        }
    }

    @Override
    public int getTotalAmountOfInvoice() {
        String sql = "SELECT count(*) FROM VietnameseInvoice";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
    public List<VietnameseInvoice> findInvoices(String name) {
        String sql = "SELECT * FROM VietnameseInvoice WHERE fullName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            List<VietnameseInvoice> invoices = new java.util.ArrayList<>();
            while (resultSet.next()) {
                VietnameseInvoice invoice = new VietnameseInvoice();
                invoice.setInvoiceId(resultSet.getInt("Id"));
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
                invoice.setInvoiceId(resultSet.getInt("Id"));
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
    public boolean deleteInvoiceById(int invoiceId) {
        String sql = "DELETE FROM VietnameseInvoice WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            statement.executeUpdate();
            return statement.getUpdateCount() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public VietnameseInvoice getInvoiceById(int invoiceId) {
        String sql = "SELECT * FROM VietnameseInvoice WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, invoiceId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                VietnameseInvoice invoice = new VietnameseInvoice();
                invoice.setInvoiceId(resultSet.getInt("Id"));
                invoice.setCustomerId(resultSet.getInt("customerId"));
                invoice.setFullName(resultSet.getString("fullName"));
                invoice.setInvoiceDate(resultSet.getDate("invoiceDate").toLocalDate());
                invoice.setQuantity(resultSet.getInt("quantity"));
                invoice.setPrice(resultSet.getInt("price"));
                invoice.setCustomerType(resultSet.getString("customerType"));
                invoice.setQuota(resultSet.getInt("quota"));
                return invoice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
