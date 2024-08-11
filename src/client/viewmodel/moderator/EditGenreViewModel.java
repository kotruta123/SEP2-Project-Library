package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class EditGenreViewModel {
    private final ClientModel model;
    private final ManageGenresViewModel manageGenresViewModel;
    private final Genre genre;
    private final StringProperty name;
    private final StringProperty image;

    public EditGenreViewModel(ClientModel model, ManageGenresViewModel manageGenresViewModel, Genre genre) {
        this.model = model;
        this.manageGenresViewModel = manageGenresViewModel;
        this.genre = genre;
        this.name = new SimpleStringProperty(genre.getName());
        this.image = new SimpleStringProperty(genre.getImage());
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public void save() {
        genre.setName(name.get());
        genre.setImage(image.get());
        try {
            model.updateGenre(genre);
            manageGenresViewModel.loadGenres(); // Ensure the genres are reloaded after saving changes
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }
}
