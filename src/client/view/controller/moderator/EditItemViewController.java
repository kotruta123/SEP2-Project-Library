package client.view.controller.moderator;

import client.viewmodel.moderator.EditItemViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;

public class EditItemViewController {

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

    private EditItemViewModel viewModel;
    private Stage stage;

    public void init(EditItemViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.stage = stage;

        titleField.textProperty().bindBidirectional(viewModel.titleProperty());
        authorField.textProperty().bindBidirectional(viewModel.authorProperty());
        priceField.textProperty().bindBidirectional(viewModel.priceProperty(), new NumberStringConverter());
        descriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());
        imageField.textProperty().bindBidirectional(viewModel.imageProperty());
        typeComboBox.setItems(viewModel.getAvailableTypes());
        typeComboBox.valueProperty().bindBidirectional(viewModel.typeProperty());
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imageField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleSave() {
        viewModel.save();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }
}
