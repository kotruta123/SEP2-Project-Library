package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.db.GenreDAO;
import server.db.ItemDAO;
import server.db.OrderDAO;
import server.db.UserDAO;
import shared.model.User;
import shared.model.Order;
import shared.model.OrderItem;
import shared.model.Genre;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDAOTest {
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private OrderDAO orderDAO;
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "sasa");

        userDAO = new UserDAO(connection); // Initialize UserDAO with the same connection
        itemDAO = new ItemDAO(connection); // Initialize ItemDAO with the same connection
        orderDAO = new OrderDAO(connection); // Initialize OrderDAO with the same connection
        // TODO: create users, items, orders and order_items tables and insert test data
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void testPlaceOrder() throws SQLException, LibraryException {
        User user = userDAO.login("alex", "alex");
        List<Item> items = itemDAO.getAllItems();

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(200.0);
        order.setOrderItems(Arrays.asList(new OrderItem(1, order, items.get(0), 10)));
        orderDAO.placeOrder(order);

        // TODO: query the order from the database and verify its properties
    }

    @Test
    public void testGetOrdersByUser() throws SQLException, LibraryException {
        User user = userDAO.login("alex", "alex");
        List<Order> orders = orderDAO.getOrdersByUser(user.getUserId());
        // TODO: verify the result
    }

    @Test
    public void testGetOrderItems() throws SQLException, LibraryException {
        List<OrderItem> orderItems = orderDAO.getOrderItems(1);
        // TODO: verify the result
    }

    @Test
    public void testDeleteOrderItemsByOrderId() throws SQLException, LibraryException {
        orderDAO.deleteOrderItemsByOrderId(1);
        // TODO: attempt to query the order items from the database and verify they do not exist
    }

    @Test
    public void testDeleteOrdersByUserId() throws SQLException, LibraryException {
        User user = userDAO.login("alex", "alex");
        orderDAO.deleteOrdersByUserId(user.getUserId());
        // TODO: attempt to query the orders from the database and verify they do not exist
    }
}