package client.viewmodel.moderator;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class ManageGenresViewModel {
    private final ClientModel model;
    private final ObservableList<Genre> genres;
    private final NavigationHelper navigationHelper;

    public ManageGenresViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.genres = FXCollections.observableArrayList();
        this.navigationHelper = navigationHelper;
        loadGenres();
    }

    public ObservableList<Genre> getGenres() {
        return genres;
    }

    public void loadGenres() {
        try {
            List<Genre> genreList = model.getAllGenres();
            genres.setAll(genreList);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void addGenre(Stage stage) {
        navigationHelper.showAddGenreView(stage);
        loadGenres(); // Ensure the genres are reloaded after adding a new genre
    }

    public void deleteGenre(Genre genre) {
        try {
            model.deleteGenre(genre.getGenreId());
            genres.remove(genre);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void editGenre(Genre genre, Stage stage) {
        navigationHelper.showEditGenreView(genre, stage);
        loadGenres(); // Ensure the genres are reloaded after editing
    }

    public void selectGenre(Genre genre) {
        if (genre != null) {
            System.out.println("Genre selected: " + genre.getName());
        } else {
            System.out.println("No genre selected.");
        }
    }

    public void viewGenre(Genre genre, Stage stage) {
        navigationHelper.showGenreDetails(genre, stage);
    }

    public void goBack(Stage primaryStage) {
        navigationHelper.goBack(primaryStage);
    }
}
