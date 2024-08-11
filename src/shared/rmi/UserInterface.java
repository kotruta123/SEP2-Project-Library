package shared.rmi;

import shared.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserInterface extends Remote {
    User login(String username, String password) throws RemoteException, LibraryException;
    void register(User user) throws RemoteException, LibraryException;
    List<User> getAllUsers() throws RemoteException, LibraryException;
    void deleteUser(int userId) throws RemoteException, LibraryException;
    User getCurrentUser() throws RemoteException, LibraryException;

    boolean isUsernameTaken(String username) throws RemoteException, LibraryException;
}
