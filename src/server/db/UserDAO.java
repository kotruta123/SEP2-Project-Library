package server.db;

import shared.model.User;
import shared.rmi.LibraryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;
    private OrderDAO orderDAO;

    public UserDAO(Connection connection) {
        this.connection = connection;
        this.orderDAO = new OrderDAO(connection); // Initialize OrderDAO with the same connection
    }

    public User login(String username, String password) throws LibraryException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("role")
                );
            } else {
                throw new LibraryException("Invalid username or password.");
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }

    public void register(User user) throws LibraryException {
        if (connection == null) {
            throw new LibraryException("Database connection is not established.");
        }

        String sql = "INSERT INTO users (username, password, name, age, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setInt(4, user.getAge());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error registering user");
        }
    }

    public List<User> getAllUsers() throws LibraryException {
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
        return users;
    }

    public void deleteUser(int userId) throws LibraryException {
        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Delete orders and related order items associated with the user
            orderDAO.deleteOrdersByUserId(userId);

            // Now delete the user
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE user_id = ?")) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback transaction in case of error
            } catch (SQLException rollbackEx) {
                throw new LibraryException("Error rolling back the transaction: " + rollbackEx.getMessage());
            }
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit mode
            } catch (SQLException e) {
                throw new LibraryException("Error resetting auto-commit mode: " + e.getMessage());
            }
        }
    }



    public boolean isUsernameExists(String username) throws LibraryException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }
}
