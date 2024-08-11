package client.view.controller.customer;

import client.viewmodel.customer.CustomerViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import shared.model.Item;

import java.io.IOException;

public class CustomerViewController {
    private CustomerViewModel viewModel;
    private Stage primaryStage;

    @FXML
    private ListView<Item> itemListView;

    public void init(CustomerViewModel viewModel, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        itemListView.itemsProperty().bind(viewModel.itemListProperty());
    }

    @FXML
    private void handleAddToCart() {
        Item selectedItem = itemListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.addToCart(selectedItem);
        }
    }

    @FXML
    private void handleCheckout() {
        viewModel.checkout(primaryStage);
    }

    @FXML
    private void handleLogout() throws IOException {
        viewModel.logout(primaryStage);
    }
}
