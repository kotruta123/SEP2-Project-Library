package client.viewmodel.customer;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.io.IOException;
import java.rmi.RemoteException;

public class CustomerViewModel {
    private final ClientModel clientModel;
    private final NavigationHelper navigationHelper;
    private final ListProperty<Item> itemList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public CustomerViewModel(ClientModel clientModel, NavigationHelper navigationHelper) {
        this.clientModel = clientModel;
        this.navigationHelper = navigationHelper;
    }

    public ListProperty<Item> itemListProperty() {
        return itemList;
    }

    public void addItem(Item item) throws LibraryException, RemoteException {
        clientModel.addItem(item);
        itemList.add(item);
    }

    public void deleteItem(Item item) throws LibraryException, RemoteException {
        clientModel.deleteItem(item.getItemId());
        itemList.remove(item);
    }

    public void logout(Stage primaryStage) throws IOException {
        clientModel.setCurrentUser(null);
        navigationHelper.showLogin(navigationHelper.getPrimaryStage());
    }

    public void checkout(Stage primaryStage) {
    }

    public void addToCart(Item selectedItem) {

    }
}
