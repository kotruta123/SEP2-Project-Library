package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class GenreDetailsViewModel {
    private final ClientModel model;
    private final Genre genre;
    private final StringProperty genreDetails;
    private final StringProperty imagePath;

    public GenreDetailsViewModel(ClientModel model, Genre genre) {
        this.model = model;
        this.genre = genre;
        this.genreDetails = new SimpleStringProperty(formatGenreDetails(genre));
        this.imagePath = new SimpleStringProperty(genre.getImage());
    }

    public StringProperty genreDetailsProperty() {
        return genreDetails;
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    private String formatGenreDetails(Genre genre) {
        return "ID: " + genre.getGenreId() + "\n" +
                "Name: " + genre.getName() + "\n" +
                "Image Path: " + genre.getImage() + "\n";
    }


}
