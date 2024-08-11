package client.viewmodel.customer;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import shared.model.Genre;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class ItemListViewModel {
    private final ClientModel clientModel;
    private final NavigationHelper navigationHelper;
    private final ListProperty<Item> itemList;

    public ItemListViewModel(ClientModel clientModel, Genre genre, NavigationHelper navigationHelper) throws LibraryException, RemoteException {
        this.clientModel = clientModel;
        this.navigationHelper = navigationHelper;
        this.itemList = new SimpleListProperty<>(FXCollections.observableArrayList(clientModel.getItemsByGenre(genre.getGenreId())));
    }

    public ListProperty<Item> itemListProperty() {
        return itemList;
    }

    public void itemSelected(Item item, Stage primaryStage) {
        navigationHelper.showItemDetailView(new Stage(), item);
    }
}
