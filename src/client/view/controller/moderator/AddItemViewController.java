package client.view.controller.moderator;

import client.viewmodel.moderator.AddItemViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shared.model.Genre;

import java.io.File;

public class AddItemViewController {
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField imageField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<Genre> genreComboBox;

    private AddItemViewModel viewModel;
    private Stage stage;

    public void init(AddItemViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.stage = stage;
        typeComboBox.setItems(viewModel.getItemTypes());
        genreComboBox.setItems(viewModel.getGenres());
    }

    @FXML
    private void handleAddItem() {
        String title = titleField.getText();
        String author = authorField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();
        String image = imageField.getText();
        String type = typeComboBox.getValue();
        Genre genre = genreComboBox.getValue();

        viewModel.addItem(title, author, price, description, image, type, genre);
        stage.close();
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            imageField.setText(selectedFile.getAbsolutePath());
        }
    }
}
