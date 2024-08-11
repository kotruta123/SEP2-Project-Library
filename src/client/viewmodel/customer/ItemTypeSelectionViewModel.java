package client.viewmodel.customer;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemTypeSelectionViewModel {
    private final ClientModel model;
    private final NavigationHelper navigationHelper;
    private final ListProperty<String> itemTypeList;

    public ItemTypeSelectionViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.navigationHelper = navigationHelper;
        this.itemTypeList = new SimpleListProperty<>(FXCollections.observableArrayList("Book", "DVD", "Magazine"));
    }

    public ListProperty<String> itemTypeListProperty() {
        return itemTypeList;
    }

    public void itemTypeSelected(String itemType, Stage stage) throws IOException {
        navigationHelper.showGenreSelectionView(stage, itemType);
    }

    public void logout(Stage primaryStage) {
        try {
            navigationHelper.showLogin(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
