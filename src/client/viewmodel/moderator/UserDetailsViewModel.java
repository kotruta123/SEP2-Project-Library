package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.model.User;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class UserDetailsViewModel {
    private final ClientModel model;
    private final User user;
    private final StringProperty userDetails;

    public UserDetailsViewModel(ClientModel model, User user) {
        this.model = model;
        this.user = user;
        this.userDetails = new SimpleStringProperty(formatUserDetails(user));
    }

    public StringProperty userDetailsProperty() {
        return userDetails;
    }

    private String formatUserDetails(User user) {
        return "ID: " + user.getUserId() + "\n" +
                "Username: " + user.getUsername() + "\n" +
                "Name: " + user.getName() + "\n" +
                "Age: " + user.getAge() + "\n" +
                "Role: " + user.getRole();
    }

    public void deleteUser() {
        try {
            model.deleteUser(user.getUserId());
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }
}
