package server.db;

import shared.model.Genre;
import shared.rmi.LibraryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private Connection connection;
    private ItemDAO itemDAO;

    public GenreDAO(Connection connection) {
        this.connection = connection;
        this.itemDAO = new ItemDAO(connection); // Initialize ItemDAO with the same connection
    }

    public List<Genre> getAllGenres() throws LibraryException {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT genre_id, name, image FROM genres";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int genreId = resultSet.getInt("genre_id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Genre genre = new Genre(genreId, name, image);
                genres.add(genre);
            }
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }

        return genres;
    }

    public void addGenre(Genre genre) throws LibraryException {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO genres (name, image) VALUES (?, ?)")) {
            stmt.setString(1, genre.getName());
            stmt.setString(2, genre.getImage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }
    public void updateGenre(Genre genre) throws LibraryException {
        String query = "UPDATE genres SET name = ?, image = ? WHERE genre_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, genre.getName());
            stmt.setString(2, genre.getImage());
            stmt.setInt(3, genre.getGenreId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new LibraryException("Error accessing the database: " + e.getMessage());
        }
    }

    public void deleteGenre(int genreId) throws LibraryException {
        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // First delete order items related to the items of this genre
            itemDAO.deleteOrderItemsByGenre(genreId);

            // Then delete items related to this genre
            itemDAO.deleteItemsByGenre(genreId);

            // Now delete the genre
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM genres WHERE genre_id = ?")) {
                stmt.setInt(1, genreId);
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
}
