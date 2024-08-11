package client.service;

import client.model.ClientModel;
import shared.model.Genre;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class GenreService {
    private ClientModel model;

    public GenreService(ClientModel model) {
        this.model = model;
    }

    public List<Genre> getAllGenres() throws RemoteException, LibraryException {
        return model.getAllGenres();
    }

    public void addGenre(Genre genre) throws RemoteException, LibraryException {
        model.addGenre(genre);
    }

    public void deleteGenre(int genreId) throws RemoteException, LibraryException {
        model.deleteGenre(genreId);
    }
}
