package org.example.adapter.database;

import org.example.domain.boundaries.out.NationalityRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class NationalityRepositoryImpl implements NationalityRepository {
    private Connection connection;

    public NationalityRepositoryImpl() {
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
    public List<String> getNationality() {
        // Retrieve nationality from the database
        String sql = "SELECT DISTINCT national FROM Nationalities";
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sql);
            List<String> nationality = new java.util.ArrayList<>();
            while (resultSet.next()) {
                nationality.add(resultSet.getString("national"));
            }
            return nationality;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
