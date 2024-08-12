package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "sasa";
    private static Connection connection;

    public static Connection initialize() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                LOGGER.info("Database connection initialized successfully.");
            } catch (SQLException e) {
                LOGGER.severe("Failed to initialize database connection: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initialize();
        }
        return connection;
    }
}



