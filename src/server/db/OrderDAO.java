package server.db;

import shared.model.Item;
import shared.model.Order;
import shared.model.OrderItem;
import shared.rmi.LibraryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void placeOrder(Order order) throws LibraryException {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO orders (user_id, total_price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, order.getUser().getUserId());
                stmt.setDouble(2, order.getTotalPrice());
                stmt.executeUpdate();

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    for (OrderItem item : order.getOrderItems()) {
                        try (PreparedStatement itemStmt = connection.prepareStatement("INSERT INTO order_items (order_id, item_id, quantity) VALUES (?, ?, ?)")) {
                            itemStmt.setInt(1, orderId);
                            itemStmt.setInt(2, item.getItem().getItemId());
                            itemStmt.setInt(3, item.getQuantity());
                            itemStmt.executeUpdate();
                        }
                    }
                } else {
                    connection.rollback();
                    throw new LibraryException("Failed to create order.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new LibraryException("Failed to rollback transaction: " + ex.getMessage());
            }
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new LibraryException("Failed to reset autocommit: " + ex.getMessage());
            }
        }
    }

    public List<Order> getOrdersByUser(int userId) throws LibraryException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        null, // User will be set later
                        rs.getDouble("total_price"),
                        null // Order items will be set later
                ));
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
        return orders;
    }

    public List<OrderItem> getOrderItems(int orderId) throws LibraryException {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM order_items WHERE order_id = ?")) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("order_item_id"),
                        new Order(orderId, null, 0, null), // Order with only ID, rest will be fetched elsewhere
                        new Item(rs.getInt("item_id"), null, null, 0, null, null, null, null), // Item with only ID, rest will be fetched elsewhere
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
        return orderItems;
    }

    public void deleteOrderItemsByOrderId(int orderId) throws LibraryException {
        String query = "DELETE FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }

    public void deleteOrdersByUserId(int userId) throws LibraryException {
        List<Order> orders = getOrdersByUser(userId);
        for (Order order : orders) {
            deleteOrderItemsByOrderId(order.getOrderId());
        }
        String query = "DELETE FROM orders WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }
}
