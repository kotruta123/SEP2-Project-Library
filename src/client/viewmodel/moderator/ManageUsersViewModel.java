package client.viewmodel.moderator;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.model.User;

import javafx.stage.Stage;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class ManageUsersViewModel {
    private final ClientModel model;
    private final NavigationHelper navigationHelper;
    private final ListProperty<User> userList;

    public ManageUsersViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.navigationHelper = navigationHelper;
        this.userList = new SimpleListProperty<>(FXCollections.observableArrayList());
        loadUsers();
    }

    public ListProperty<User> userListProperty() {
        return userList;
    }

    private void loadUsers() {
        try {
            List<User> users = model.getAllUsers();
            userList.setAll(users);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            model.deleteUser(user.getUserId());
            userList.remove(user);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void showUserDetails(User user, Stage primaryStage) {
        navigationHelper.showUserDetails(user, primaryStage);
    }

    public void goBack(Stage primaryStage) {
        navigationHelper.goBack(primaryStage);
    }
}
