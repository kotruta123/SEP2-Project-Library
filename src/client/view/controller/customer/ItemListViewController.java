package client.view.controller.customer;

import client.NavigationHelper;
import client.viewmodel.customer.ItemListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import shared.model.Item;

public class ItemListViewController {
    private ItemListViewModel viewModel;
    private Stage primaryStage;
    private NavigationHelper navigationHelper;

    @FXML
    private ListView<Item> itemListView;

    public void init(ItemListViewModel viewModel, Stage primaryStage, NavigationHelper navigationHelper) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        this.navigationHelper = navigationHelper;

        itemListView.itemsProperty().bind(viewModel.itemListProperty());
        itemListView.setCellFactory(param -> new ItemListCell());
        itemListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleItemSelected();
            }
        });
    }

    @FXML
    private void handleItemSelected() {
        Item selectedItem = itemListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.itemSelected(selectedItem, primaryStage);
        }
    }

    @FXML
    private void handleGoToItemTypeSelection() {
        navigationHelper.showItemTypeSelectionView(primaryStage);
    }
}
