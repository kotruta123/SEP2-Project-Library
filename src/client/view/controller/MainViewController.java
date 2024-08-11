package client.view.controller;

import client.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shared.rmi.LibraryException;

import java.io.IOException;
import java.rmi.RemoteException;

public class MainViewController {
    private MainViewModel viewModel;
    private Stage primaryStage;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void init(MainViewModel viewModel, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        usernameField.textProperty().bindBidirectional(viewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            showAlert("Validation Error", "Username cannot be empty");
            return;
        }

        if (password == null || password.isEmpty()) {
            showAlert("Validation Error", "Password cannot be empty");
            return;
        }

        try {
            viewModel.login(primaryStage);
        } catch (LibraryException | IOException e) {
            showAlert("Login Error", e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        try {
            viewModel.register(primaryStage);
        } catch (IOException e) {
            showAlert("Navigation Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
