package org.detteapp.odc.config;

import org.detteapp.odc.database.DatabaseInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.*;


@Component
public class DatabaseConfig implements DatabaseInterface {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    private Connection connection;


    @Override
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try{
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        int affectedRows = 0;
        try {

        Statement statement = connection.createStatement();
        affectedRows = statement.executeUpdate(sql);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return affectedRows;
    }
}
