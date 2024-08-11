package client.view.controller.customer;

import client.NavigationHelper;
import client.viewmodel.customer.ItemTypeSelectionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemTypeSelectionViewController {
    private ItemTypeSelectionViewModel viewModel;
    private Stage primaryStage;
    private NavigationHelper navigationHelper;

    @FXML
    private ListView<String> itemTypeListView;

    public void init(ItemTypeSelectionViewModel viewModel, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        itemTypeListView.itemsProperty().bind(viewModel.itemTypeListProperty());
    }

    @FXML
    private void handleItemTypeSelected() {
        String selectedItemType = itemTypeListView.getSelectionModel().getSelectedItem();
        if (selectedItemType != null) {
            try {
                viewModel.itemTypeSelected(selectedItemType, primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
                // Show error message to user
            }
        }
    }
    @FXML
    private void handleLogout() {
        viewModel.logout(primaryStage);
    }
}
