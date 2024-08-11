package client.view.controller.moderator;

import client.viewmodel.moderator.GenreDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GenreDetailsViewController {
    @FXML
    private Label genreDetailsLabel;

    @FXML
    private ImageView genreImageView;

    private GenreDetailsViewModel viewModel;
    private Stage dialogStage;

    public void init(GenreDetailsViewModel viewModel, Stage dialogStage) {
        this.viewModel = viewModel;
        this.dialogStage = dialogStage;

        genreDetailsLabel.textProperty().bind(viewModel.genreDetailsProperty());
        genreImageView.setImage(new Image("file:" + viewModel.imagePathProperty().get()));
    }



    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
