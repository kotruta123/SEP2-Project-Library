package client.view.controller.customer;

import client.NavigationHelper;
import client.viewmodel.customer.GenreSelectionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import shared.model.Genre;

public class GenreSelectionViewController {
    private GenreSelectionViewModel viewModel;
    private Stage primaryStage;
    private NavigationHelper navigationHelper;

    public void init(GenreSelectionViewModel viewModel, NavigationHelper navigationHelper, Stage primaryStage) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        this.navigationHelper = navigationHelper;
        genreListView.itemsProperty().bind(viewModel.genreListProperty());
    }

    @FXML
    private ListView<Genre> genreListView;

    @FXML
    private void handleGenreSelected() {
        Genre selectedGenre = genreListView.getSelectionModel().getSelectedItem();
        if (selectedGenre != null) {
            viewModel.genreSelected(selectedGenre, primaryStage);
        }
    }

    @FXML
    private void handleBack() {
        navigationHelper.showItemTypeSelectionView(primaryStage);
    }
}
