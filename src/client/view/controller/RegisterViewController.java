package client.view.controller;

import client.viewmodel.RegisterViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shared.rmi.LibraryException;

import java.io.IOException;
import java.rmi.RemoteException;

public class RegisterViewController {
    private RegisterViewModel viewModel;
    private Stage primaryStage;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Integer> ageSpinner;

    public void init(RegisterViewModel viewModel, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        usernameField.textProperty().bindBidirectional(viewModel.usernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        nameField.textProperty().bindBidirectional(viewModel.nameProperty());

        SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 18);
        ageSpinner.setValueFactory(ageValueFactory);

        Bindings.bindBidirectional(viewModel.ageProperty().asObject(), ageValueFactory.valueProperty());
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        Integer age = ageSpinner.getValue();

        if (username == null || username.isEmpty()) {
            showAlert("Validation Error", "Username cannot be empty");
            return;
        }

        if (password == null || password.isEmpty()) {
            showAlert("Validation Error", "Password cannot be empty");
            return;
        }

        if (password.length() < 6) {
            showAlert("Validation Error", "Password must be at least 6 characters long");
            return;
        }

        if (name == null || name.isEmpty()) {
            showAlert("Validation Error", "Name cannot be empty");
            return;
        }

        if (age == null || age < 5 || age > 100) {
            showAlert("Validation Error", "Age must be between 5 and 100");
            return;
        }

        try {
            if (viewModel.isUsernameTaken(username)) {
                showAlert("Validation Error", "Username is already taken");
                return;
            }
            viewModel.register(primaryStage);
        } catch (IOException | LibraryException e) {
            e.printStackTrace();
            showAlert("Registration Error", e.getMessage());
        }
    }

    @FXML
    private void handleLogin() {
        try {
            viewModel.showLogin(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
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
