package client.view.controller.customer;

import client.NavigationHelper;
import client.viewmodel.customer.ItemDetailViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ItemDetailViewController {
    private ItemDetailViewModel viewModel;
    private NavigationHelper navigationHelper;
    private Stage stage;

    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Spinner<Integer> quantitySpinner;
    @FXML
    private Button addToCartButton;

    public void init(ItemDetailViewModel viewModel, NavigationHelper navigationHelper, Stage stage) {
        this.viewModel = viewModel;
        this.navigationHelper = navigationHelper;
        this.stage = stage;

        titleLabel.textProperty().bind(viewModel.titleProperty());
        authorLabel.textProperty().bind(viewModel.authorProperty());
        priceLabel.textProperty().bind(viewModel.priceProperty().asString());
        descriptionLabel.textProperty().bind(viewModel.descriptionProperty());

        if (viewModel.imageProperty().get() != null && !viewModel.imageProperty().get().isEmpty()) {
            imageView.setImage(new Image("file:" + viewModel.imageProperty().get()));
        }

        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    @FXML
    private void handleAddToCart() {
        int quantity = quantitySpinner.getValue();
        viewModel.addToCart(quantity);
        navigationHelper.showCartView(stage);
    }

    @FXML
    private void handleGoToCart() {
        navigationHelper.showCartView(stage);
    }

    @FXML
    private void handleBack() {
        stage.close();
    }
}
