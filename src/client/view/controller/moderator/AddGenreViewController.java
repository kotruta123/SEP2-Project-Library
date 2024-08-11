package client.view.controller.moderator;

import client.viewmodel.moderator.AddGenreViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddGenreViewController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField imageField;

    private AddGenreViewModel viewModel;
    private Stage dialogStage;
    private File chosenFile;

    public void init(AddGenreViewModel viewModel, Stage dialogStage) {
        this.viewModel = viewModel;
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleAddGenre() {
        String name = nameField.getText();
        if (chosenFile != null) {
            viewModel.addGenre(name, chosenFile);
            dialogStage.close();
        } else {
            System.out.println("No image file chosen.");
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        chosenFile = fileChooser.showOpenDialog(dialogStage);
        if (chosenFile != null) {
            imageField.setText(chosenFile.getAbsolutePath());
        }
    }
}
