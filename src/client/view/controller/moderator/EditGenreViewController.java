package client.view.controller.moderator;

import client.viewmodel.moderator.EditGenreViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EditGenreViewController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField imageField;

    private EditGenreViewModel viewModel;
    private Stage stage;

    public void init(EditGenreViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.stage = stage;
        nameField.textProperty().bindBidirectional(viewModel.nameProperty());
        imageField.textProperty().bindBidirectional(viewModel.imageProperty());
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            viewModel.setImage(file.getAbsolutePath());
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
