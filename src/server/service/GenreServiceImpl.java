package server.service;

import shared.model.Genre;
import shared.rmi.GenreInterface;
import shared.rmi.LibraryException;
import server.db.GenreDAO;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class GenreServiceImpl implements GenreInterface, Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID
    private GenreDAO genreDAO;

    public GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public List<Genre> getAllGenres() throws RemoteException, LibraryException {
        return genreDAO.getAllGenres();
    }

    @Override
    public void addGenre(Genre genre) throws RemoteException, LibraryException {
        genreDAO.addGenre(genre);
    }
    @Override
    public void updateGenre(Genre genre) throws RemoteException, LibraryException {
        genreDAO.updateGenre(genre);
    }

    @Override
    public void deleteGenre(int genreId) throws RemoteException, LibraryException {
        genreDAO.deleteGenre(genreId);
    }
}
