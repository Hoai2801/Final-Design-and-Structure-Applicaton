package org.example.adapter.database;

import org.example.domain.boundaries.out.ForeignInvoiceRepository;
import org.example.domain.entities.ForeignInvoice;

import java.sql.*;
import java.util.List;

public class ForeignRepository implements ForeignInvoiceRepository {

    private Connection connection;

    public ForeignRepository() {
        // Connect to MySQL database
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/invoice",
                    "root",
                    "password");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getErrorCode());
        }
    }

    @Override
    public boolean createInvoice(ForeignInvoice invoice) {
        String sql = "INSERT INTO ForeignInvoice (customerId, fullName, invoiceDate, quantity, price, nationality) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(invoice.getCustomerId()));
            statement.setString(2, invoice.getFullName());
            statement.setDate(3, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            statement.setInt(4, invoice.getQuantity());
            statement.setDouble(5, invoice.getPrice());
            statement.setString(6, invoice.getNationality());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteInvoice(ForeignInvoice invoice) {
        String sql = "DELETE FROM ForeignInvoice WHERE customerId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(invoice.getCustomerId()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateInvoice(ForeignInvoice invoice) {
        String sql = "UPDATE ForeignInvoice SET fullName = ?, invoiceDate = ?, quantity = ?, price = ?, nationality = ? WHERE customerId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, invoice.getFullName());
            statement.setDate(2, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            statement.setInt(3, invoice.getQuantity());
            statement.setDouble(4, invoice.getPrice());
            statement.setString(5, invoice.getNationality());
            statement.setString(6, String.valueOf(invoice.getCustomerId()));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public int getAmountOfInvoices() {
        // count all invoices
        String sql = "SELECT COUNT(*) FROM ForeignInvoice";
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
    public double getAveragePriceOfInvoices() {
        String sql = "SELECT AVG(price * quantity) FROM ForeignInvoice;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getTotalAmountOfInvoiceByMonth(int month) {
        String sql = "SELECT COUNT(*) FROM ForeignInvoice WHERE MONTH(invoiceDate) = ?";
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
    public List<ForeignInvoice> findInvoices(String query) {
        String sql = "SELECT * FROM ForeignInvoice WHERE fullName LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            List<ForeignInvoice> invoices = new java.util.ArrayList<>();
            while (resultSet.next()) {
                ForeignInvoice invoice = new ForeignInvoice();
                invoice.setCustomerId(resultSet.getInt("customerId"));
                invoice.setFullName(resultSet.getString("fullName"));
                invoice.setInvoiceDate(resultSet.getDate("invoiceDate").toLocalDate());
                invoice.setQuantity(resultSet.getInt("quantity"));
                invoice.setPrice(resultSet.getInt("price"));
                invoice.setNationality(resultSet.getString("nationality"));
                invoices.add(invoice);
            }
            return invoices;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ForeignInvoice> getInvoices() {
        String sql = "SELECT * FROM ForeignInvoice";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<ForeignInvoice> invoices = new java.util.ArrayList<>();
            while (resultSet.next()) {
                ForeignInvoice invoice = new ForeignInvoice();
                invoice.setCustomerId(resultSet.getInt("customerId"));
                invoice.setFullName(resultSet.getString("fullName"));
                invoice.setInvoiceDate(resultSet.getDate("invoiceDate").toLocalDate());
                invoice.setQuantity(resultSet.getInt("quantity"));
                invoice.setPrice(resultSet.getInt("price"));
                invoice.setNationality(resultSet.getString("nationality"));
                invoices.add(invoice);
            }
            return invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
