package server.db;

import shared.model.Genre;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Item> getItemsByGenre(int genreId) throws LibraryException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT i.item_id, i.title, i.author, i.price, i.description, i.image, i.type, g.genre_id, g.name, g.image " +
                "FROM items i JOIN genres g ON i.genre_id = g.genre_id WHERE i.genre_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, genreId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("item_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    String image = resultSet.getString("image");
                    String type = resultSet.getString("type");

                    int gId = resultSet.getInt("genre_id");
                    String gName = resultSet.getString("name");
                    String gImage = resultSet.getString("image");

                    Genre genre = new Genre(gId, gName, gImage);
                    Item item = new Item(itemId, title, author, price, description, image, type, genre);
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }

        return items;
    }

    public List<Item> getAllItems() throws LibraryException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT i.item_id, i.title, i.author, i.price, i.description, i.image, i.type, g.genre_id, g.name, g.image " +
                "FROM items i JOIN genres g ON i.genre_id = g.genre_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                String type = resultSet.getString("type");

                int gId = resultSet.getInt("genre_id");
                String gName = resultSet.getString("name");
                String gImage = resultSet.getString("image");

                Genre genre = new Genre(gId, gName, gImage);
                Item item = new Item(itemId, title, author, price, description, image, type, genre);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }

        return items;
    }

    public void addItem(Item item) throws LibraryException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO items (title, author, price, description, image, type, genre_id) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, item.getTitle());
            stmt.setString(2, item.getAuthor());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getDescription());
            stmt.setString(5, item.getImage());
            stmt.setString(6, item.getType());
            stmt.setInt(7, item.getGenre().getGenreId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }

    public void deleteItem(int itemId) throws LibraryException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM items WHERE item_id = ?")) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }
    public void updateItem(Item item) throws LibraryException {
        String query = "UPDATE items SET title = ?, author = ?, price = ?, description = ?, image = ?, type = ?, genre_id = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getTitle());
            stmt.setString(2, item.getAuthor());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getDescription());
            stmt.setString(5, item.getImage());
            stmt.setString(6, item.getType());
            stmt.setInt(7, item.getGenre().getGenreId());
            stmt.setInt(8, item.getItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error updating the item: " + e.getMessage());
        }
    }


    public void deleteOrderItemsByGenre(int genreId) throws LibraryException {
        String query = "DELETE FROM order_items WHERE item_id IN (SELECT item_id FROM items WHERE genre_id = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, genreId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }

    public void deleteItemsByGenre(int genreId) throws LibraryException {
        String query = "DELETE FROM items WHERE genre_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, genreId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }
}
