package org.example.adapter.database;

import org.example.domain.boundaries.out.CustomerTypeRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CustomerTypeRepositoryImpl implements CustomerTypeRepository {
    private Connection connection;

    public CustomerTypeRepositoryImpl() {
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
    public List<String> getCustomerTypes() {
        // Retrieve customer types from the database
        String sql = "SELECT DISTINCT type FROM CustomerObject";
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            List<String> customerTypes = new java.util.ArrayList<>();
            while (resultSet.next()) {
                customerTypes.add(resultSet.getString("type"));
            }
            return customerTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
