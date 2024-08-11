package client.viewmodel;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import shared.model.User;
import shared.rmi.LibraryException;

import java.io.IOException;
import java.rmi.RemoteException;

public class MainViewModel {
    private final ClientModel model;
    private final NavigationHelper navigationHelper;
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public MainViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.navigationHelper = navigationHelper;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void login(Stage primaryStage) throws RemoteException, LibraryException, IOException {
        String user = username.get();
        String pass = password.get();
        User currentUser = model.login(user, pass);
        navigationHelper.setCurrentUser(currentUser);

        if ("customer".equals(currentUser.getRole())) {
            navigationHelper.showItemTypeSelectionView(primaryStage);
        } else if ("admin".equals(currentUser.getRole())) {
            navigationHelper.showModeratorMainView(primaryStage, currentUser);
        }
    }

    public void register(Stage primaryStage) throws IOException {
        navigationHelper.showRegister(primaryStage);
    }
}
