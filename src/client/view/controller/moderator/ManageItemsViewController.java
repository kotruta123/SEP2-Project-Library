package client.view.controller.moderator;

import client.viewmodel.moderator.ManageItemsViewModel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shared.model.Item;

public class ManageItemsViewController {

    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, Integer> itemIdColumn;
    @FXML
    private TableColumn<Item, String> titleColumn;
    @FXML
    private TableColumn<Item, String> authorColumn;
    @FXML
    private TableColumn<Item, Double> priceColumn;
    @FXML
    private TableColumn<Item, String> descriptionColumn;
    @FXML
    private TableColumn<Item, String> typeColumn;

    private ManageItemsViewModel viewModel;
    private Stage stage;

    public void init(ManageItemsViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.stage = stage;
        itemTableView.setItems(viewModel.getItems());

        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        viewModel.loadItems();
        viewModel.getItems().addListener((ListChangeListener<Item>) change -> {
            while (change.next()) {
                if (change.wasUpdated()) {
                    itemTableView.refresh();
                }
            }
        });
    }

    @FXML
    private void handleViewItem() {
        Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.viewItem(selectedItem, stage);
        }
    }

    @FXML
    private void handleDeleteItem() {
        Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.deleteItem(selectedItem);
        }
    }

    @FXML
    private void handleAddItem() {
        viewModel.addItem(stage);
    }

    @FXML
    private void handleEditItem() {
        Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.editItem(selectedItem, stage);
        }
    }

    @FXML
    private void handleBack() {
        viewModel.goBack(stage);
    }
}
