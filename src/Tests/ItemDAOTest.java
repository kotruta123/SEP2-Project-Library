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
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetItemsByGenre() throws SQLException, LibraryException {
        List<Item> items = itemDAO.getItemsByGenre(1);
    }

    @Test
    public void testGetAllItems() throws SQLException, LibraryException {
        List<Item> items = itemDAO.getAllItems();
    }

    @Test
    public void testAddItem() throws SQLException, LibraryException {
        Genre genre = new Genre(4, "Fantasy", "image.jpg");
        Item item = new Item(1, "title", "author", 10.0, "description", "image", "CD", genre);
        itemDAO.addItem(item);
    }

    @Test
    public void testDeleteItem() throws SQLException, LibraryException {
        itemDAO.deleteItem(1);
    }

    @Test
    public void testDeleteOrderItemsByGenre() throws SQLException, LibraryException {
        itemDAO.deleteOrderItemsByGenre(1);
    }

    @Test
    public void testDeleteItemsByGenre() throws SQLException, LibraryException {
        itemDAO.deleteItemsByGenre(1);
    }
}