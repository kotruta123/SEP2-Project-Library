package shared.rmi;

import shared.model.Item;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ItemInterface extends Remote {
    List<Item> getItemsByGenre(int genreId) throws RemoteException, LibraryException;
    void addItem(Item item) throws RemoteException, LibraryException;
    void deleteItem(int itemId) throws RemoteException, LibraryException;
    void updateItem(Item item) throws RemoteException, LibraryException;
    List<Item> getAllItems() throws RemoteException, LibraryException;
}
