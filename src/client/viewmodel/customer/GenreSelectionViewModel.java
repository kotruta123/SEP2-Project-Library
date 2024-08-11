package client.viewmodel.customer;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class GenreSelectionViewModel {
    private final ClientModel model;
    private final NavigationHelper navigationHelper;
    private final ListProperty<Genre> genreList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public GenreSelectionViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.navigationHelper = navigationHelper;
        loadGenres();
    }

    public ListProperty<Genre> genreListProperty() {
        return genreList;
    }

    private void loadGenres() {
        try {
            List<Genre> genres = model.getAllGenres();
            genreList.setAll(genres);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void genreSelected(Genre genre, Stage stage) {
        navigationHelper.showItemListView(stage, genre);
    }
}
