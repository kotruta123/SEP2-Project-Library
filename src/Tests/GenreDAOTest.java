package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.db.GenreDAO;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenreDAOTest {
    private GenreDAO genreDAO;
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "sasa");        genreDAO = new GenreDAO(connection);
        // TODO: create genres table and insert test data
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetAllGenres() throws SQLException, LibraryException {
        List<Genre> genres = genreDAO.getAllGenres();
        // TODO: verify the result
    }

    @Test
    public void testAddGenre() throws SQLException, LibraryException {
        Genre newGenre = new Genre(2, "New genre", "newgenre.jpg");
        genreDAO.addGenre(newGenre);
        // TODO: query the genre from the database and verify its properties
    }

    @Test
    public void testDeleteGenre() throws SQLException, LibraryException {
        genreDAO.deleteGenre(1);
        // TODO: attempt to query the genre from the database and verify it does not exist
    }
}