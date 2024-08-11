package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.db.ItemDAO;
import shared.model.Genre;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemDAOTest {
    private ItemDAO itemDAO;
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "sasa");
        itemDAO = new ItemDAO(connection);
        // TODO: create item and genre tables, insert test data
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetItemsByGenre() throws SQLException, LibraryException {
        List<Item> items = itemDAO.getItemsByGenre(1);
        // TODO: verify the result
    }

    @Test
    public void testGetAllItems() throws SQLException, LibraryException {
        List<Item> items = itemDAO.getAllItems();
        // TODO: verify the result
    }

    @Test
    public void testAddItem() throws SQLException, LibraryException {
        Genre genre = new Genre(1, "Fantasy", "image.jpg");
        Item item = new Item(1, "title", "author", 10.0, "description", "image", "type", genre);
        itemDAO.addItem(item);
        // TODO: query the item from the database and verify its properties
    }

    @Test
    public void testDeleteItem() throws SQLException, LibraryException {
        itemDAO.deleteItem(1);
        // TODO: attempt to query the item from the database and verify it does not exist
    }

    @Test
    public void testDeleteOrderItemsByGenre() throws SQLException, LibraryException {
        itemDAO.deleteOrderItemsByGenre(1);
        // TODO: verify the order items by genre are deleted
    }

    @Test
    public void testDeleteItemsByGenre() throws SQLException, LibraryException {
        itemDAO.deleteItemsByGenre(1);
        // TODO: query the items by genre from the database and verify they do not exist
    }
}