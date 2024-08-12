package client.viewmodel.moderator;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class ManageItemsViewModel {
    private final ClientModel clientModel;
    private final ObservableList<Item> items;
    private final NavigationHelper navigationHelper;

    public ManageItemsViewModel(ClientModel clientModel, NavigationHelper navigationHelper) {
        this.clientModel = clientModel;
        this.items = FXCollections.observableArrayList();
        this.navigationHelper = navigationHelper;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void loadItems() {
        try {
            List<Item> itemList = clientModel.getAllItems();
            items.setAll(itemList);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(Item item) {
        try {
            clientModel.deleteItem(item.getItemId());
            items.remove(item);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void editItem(Item item, Stage stage) {
        navigationHelper.showEditItemView(item, stage);
        loadItems();
    }

    public void selectItem(Item item) {
        if (item != null) {
            System.out.println("Item selected: " + item.getTitle());
        } else {
            System.out.println("No item selected.");
        }
    }

    public void viewItem(Item item, Stage stage) {
        navigationHelper.showItemDetails(item, stage);
    }

    public void addItem(Stage stage) {
        navigationHelper.showAddItemView(stage);
        loadItems(); // Ensure the items are reloaded after adding a new item
    }

    public void goBack(Stage primaryStage) {
        navigationHelper.goBack(primaryStage);
    }
}
