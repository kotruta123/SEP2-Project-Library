package shared.rmi;

import shared.model.Genre;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GenreInterface extends Remote {
    List<Genre> getAllGenres() throws RemoteException, LibraryException;
    void addGenre(Genre genre) throws RemoteException, LibraryException;
    void updateGenre(Genre genre) throws RemoteException, LibraryException;
    void deleteGenre(int genreId) throws RemoteException, LibraryException;
}
