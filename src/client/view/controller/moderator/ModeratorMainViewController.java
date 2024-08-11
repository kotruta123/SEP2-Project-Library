package client.view.controller.moderator;

import client.viewmodel.moderator.ModeratorMainViewModel;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ModeratorMainViewController {
    private ModeratorMainViewModel viewModel;
    private Stage primaryStage;

    public void init(ModeratorMainViewModel viewModel, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleManageUsers() {
        viewModel.showManageUsers(primaryStage);
    }

    @FXML
    private void handleManageGenres() {
        viewModel.showManageGenres(primaryStage);
    }

    @FXML
    private void handleManageItems() {
        viewModel.showManageItems(primaryStage);
    }

    @FXML
    private void handleLogout() {
        viewModel.logout(primaryStage);
    }
}
