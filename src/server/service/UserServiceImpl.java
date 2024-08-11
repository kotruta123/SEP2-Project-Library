package server.service;

import shared.model.User;
import shared.rmi.LibraryException;
import shared.rmi.UserInterface;
import server.db.UserDAO;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class UserServiceImpl implements UserInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User login(String username, String password) throws RemoteException, LibraryException {
        return userDAO.login(username, password);
    }

    @Override
    public void register(User user) throws RemoteException, LibraryException {
        userDAO.register(user);
    }

    @Override
    public List<User> getAllUsers() throws RemoteException, LibraryException {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteUser(int userId) throws RemoteException, LibraryException {
        userDAO.deleteUser(userId);
    }

    @Override
    public User getCurrentUser() throws RemoteException, LibraryException {
        return null;
    }

    @Override
    public boolean isUsernameTaken(String username) throws RemoteException, LibraryException {

        return userDAO.isUsernameExists(username);
    }
}
