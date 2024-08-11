package client.service;

import client.model.ClientModel;
import shared.model.User;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class UserService {
    private ClientModel model;

    public UserService(ClientModel model) {
        this.model = model;
    }

    public User login(String username, String password) throws RemoteException, LibraryException {
        return model.login(username, password);
    }

    public void register(User user) throws RemoteException, LibraryException {
        model.register(user);
    }

    public User getCurrentUser() {
        return model.getCurrentUser();
    }
}
