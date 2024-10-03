package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DatabaseConfig.URL,

                    DatabaseConfig.USER, DatabaseConfig.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos",

                    e);
        }
    }
}