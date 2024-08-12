package client.view.controller.customer;

import client.NavigationHelper;
import client.viewmodel.customer.CartViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shared.model.OrderItem;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class CartViewController {
    @FXML
    private TableView<OrderItem> cartTableView;
    @FXML
    private TableColumn<OrderItem, String> itemTypeColumn;
    @FXML
    private TableColumn<OrderItem, String> nameColumn;
    @FXML
    private TableColumn<OrderItem, String> authorColumn;
    @FXML
    private TableColumn<OrderItem, Integer> quantityColumn;
    @FXML
    private Label totalPriceLabel;

    private CartViewModel viewModel;
    private NavigationHelper navigationHelper;
    private Stage primaryStage;
    private final StringProperty totalPriceProperty = new SimpleStringProperty();

    public void init(CartViewModel viewModel, Stage primaryStage, NavigationHelper navigationHelper) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        this.navigationHelper = navigationHelper;

        itemTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItem().getType()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItem().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItem().getAuthor()));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cartTableView.setItems(viewModel.getCartItems());
        totalPriceLabel.textProperty().bind(totalPriceProperty);
        updateTotalPrice();

        viewModel.getCartItems().addListener((javafx.collections.ListChangeListener<OrderItem>) c -> updateTotalPrice());
    }

    @FXML
    private void handlePlaceOrder() throws LibraryException, RemoteException {
        viewModel.placeOrder();
        updateTotalPrice();
        navigationHelper.showOrderConfirmation(primaryStage);
    }

    @FXML
    private void handleRemoveSelectedItem() {
        OrderItem selectedItem = cartTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            viewModel.removeFromCart(selectedItem);
            updateTotalPrice();
        }
    }

    private void updateTotalPrice() {
        totalPriceProperty.set(String.format("$%.2f", viewModel.getTotalPrice()));
    }
}
