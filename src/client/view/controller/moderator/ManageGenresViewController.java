package client.view.controller.moderator;

import client.NavigationHelper;
import client.viewmodel.moderator.ManageGenresViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shared.model.Genre;

public class ManageGenresViewController {

    @FXML
    private TableView<Genre> genreTableView;
    @FXML
    private TableColumn<Genre, Integer> genreIdColumn;
    @FXML
    private TableColumn<Genre, String> genreNameColumn;
    @FXML
    private TableColumn<Genre, String> genreImageColumn;

    private ManageGenresViewModel viewModel;
    private Stage primaryStage;
    private NavigationHelper navigationHelper;

    public void init(ManageGenresViewModel viewModel, Stage primaryStage, NavigationHelper navigationHelper) {
        this.viewModel = viewModel;
        this.primaryStage = primaryStage;
        this.navigationHelper = navigationHelper;

        genreIdColumn.setCellValueFactory(new PropertyValueFactory<>("genreId"));
        genreNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));

        genreTableView.setItems(viewModel.getGenres());

        viewModel.getGenres().addListener((javafx.collections.ListChangeListener<Genre>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    genreTableView.setItems(viewModel.getGenres());
                }
            }
        });
    }

    @FXML
    private void handleAddGenre() {
        viewModel.addGenre(primaryStage);
    }

    @FXML
    private void handleViewGenre() {
        Genre selectedGenre = genreTableView.getSelectionModel().getSelectedItem();
        if (selectedGenre != null) {
            viewModel.viewGenre(selectedGenre, primaryStage);
        }
    }

    @FXML
    private void handleEditGenre() {
        Genre selectedGenre = genreTableView.getSelectionModel().getSelectedItem();
        if (selectedGenre != null) {
            viewModel.editGenre(selectedGenre, primaryStage);
        }
    }

    @FXML
    private void handleDeleteGenre() {
        Genre selectedGenre = genreTableView.getSelectionModel().getSelectedItem();
        if (selectedGenre != null) {
            viewModel.deleteGenre(selectedGenre);
        }
    }

    @FXML
    private void handleBack() {
        viewModel.goBack(primaryStage);
    }
}
