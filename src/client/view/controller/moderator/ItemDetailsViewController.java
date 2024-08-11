package client.view.controller.moderator;

import client.viewmodel.moderator.ItemDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ItemDetailsViewController {
    @FXML
    private Label itemDetailsLabel;

    private ItemDetailsViewModel viewModel;
    private Stage stage;

    public void init(ItemDetailsViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.stage = stage;
        itemDetailsLabel.textProperty().bind(viewModel.itemDetailsProperty());
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }
}
