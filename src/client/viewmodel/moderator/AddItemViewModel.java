package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Genre;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.util.List;

public class AddItemViewModel {
    private final ClientModel clientModel;
    private final ObservableList<String> itemTypes;
    private final ObservableList<Genre> genres;

    public AddItemViewModel(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.itemTypes = FXCollections.observableArrayList("book", "magazine", "DVD");
        this.genres = FXCollections.observableArrayList();
        loadGenres();
    }

    public ObservableList<String> getItemTypes() {
        return itemTypes;
    }

    public ObservableList<Genre> getGenres() {
        return genres;
    }

    private void loadGenres() {
        try {
            List<Genre> genreList = clientModel.getAllGenres();
            genres.setAll(genreList);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String title, String author, double price, String description, String imagePath, String type, Genre genre) {
        try {
            // Copy the selected image to the server's images directory
            File source = new File(imagePath);
            File dest = new File("C:/Users/Alexandru/Desktop/SEP2-Project-Library/others/server_images" + source.getName());
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Create the new item with the path to the copied image
            Item newItem = new Item(0, title, author, price, description, dest.getPath(), type, genre);
            clientModel.addItem(newItem);
        } catch (LibraryException | IOException e) {
            e.printStackTrace();
        }
    }
}
