package client.view.controller.moderator;

import client.NavigationHelper;
import client.viewmodel.moderator.ManageUsersViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import shared.model.User;

public class ManageUsersViewController {
    @FXML
    private TableView<User> userTableView;

    private ManageUsersViewModel viewModel;
    private Stage primaryStage;
    private NavigationHelper navigationHelper;

    public void init(ManageUsersViewModel viewModel, Stage primaryStage, NavigationHelper navigationHelper) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        this.navigationHelper = navigationHelper;
        userTableView.itemsProperty().bind(viewModel.userListProperty());
    }

    @FXML
    private void handleViewUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            viewModel.showUserDetails(selectedUser, primaryStage);
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            viewModel.deleteUser(selectedUser);
        }
    }

    @FXML
    private void handleBack() {
        viewModel.goBack(primaryStage);
    }
}
