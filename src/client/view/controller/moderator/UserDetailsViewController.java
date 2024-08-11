package client.view.controller.moderator;

import client.viewmodel.moderator.UserDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserDetailsViewController {
    private UserDetailsViewModel viewModel;
    private Stage dialogStage;

    @FXML
    private Label userDetailsLabel;

    public void init(UserDetailsViewModel viewModel, Stage dialogStage) {
        this.viewModel = viewModel;
        this.dialogStage = dialogStage;
        userDetailsLabel.textProperty().bind(viewModel.userDetailsProperty());
    }

    @FXML
    private void handleDeleteUser() {
        viewModel.deleteUser();
        dialogStage.close();
    }

    @FXML
    private void handleClose() {
        dialogStage.close();
    }
}
