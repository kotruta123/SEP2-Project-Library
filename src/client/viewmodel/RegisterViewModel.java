package client.viewmodel;

import client.NavigationHelper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import shared.model.User;
import shared.rmi.LibraryException;
import shared.rmi.UserInterface;

import java.io.IOException;
import java.rmi.RemoteException;

public class RegisterViewModel {
    private final UserInterface userService;
    private final NavigationHelper navigationHelper;
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();

    public RegisterViewModel(UserInterface userService, NavigationHelper navigationHelper) {
        this.userService = userService;
        this.navigationHelper = navigationHelper;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void register(Stage primaryStage) throws IOException, LibraryException {
        User user = new User(0, username.get(), password.get(), name.get(), age.get(), "customer");
        userService.register(user);
        navigationHelper.showLogin(primaryStage);
    }

    public void showLogin(Stage primaryStage) throws IOException {
        navigationHelper.showLogin(primaryStage);
    }

    public boolean isUsernameTaken(String username) throws RemoteException, LibraryException {
        return userService.isUsernameTaken(username);
    }
}
