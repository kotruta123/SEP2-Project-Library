package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.db.UserDAO;
import shared.model.User;
import shared.rmi.LibraryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest {
    private UserDAO userDAO;
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "sasa");        userDAO = new UserDAO(connection);
    }

    @AfterEach
    public void teardown() throws SQLException {
        connection.close();
    }

    @Test
    public void testLogin() throws SQLException, LibraryException {
        User user = userDAO.login("alex", "alex");
    }

    @Test
    public void testRegister() throws SQLException, LibraryException {
        User newUser = new User(2, "newuser2", "newpwd", "New User", 30, "user");
        userDAO.register(newUser);
    }

    @Test
    public void testGetAllUsers() throws SQLException, LibraryException {
        List<User> users = userDAO.getAllUsers();
    }

    @Test
    public void testDeleteUser() throws SQLException, LibraryException {
        userDAO.deleteUser(1);
    }

    @Test
    public void testIsUsernameExists() throws SQLException, LibraryException {
        boolean exists = userDAO.isUsernameExists("testuser");
    }
}