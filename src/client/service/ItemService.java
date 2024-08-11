package client.service;

import client.model.ClientModel;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class ItemService {
    private ClientModel model;

    public ItemService(ClientModel model) {
        this.model = model;
    }

    public List<Item> getItemsByGenre(int genreId) throws RemoteException, LibraryException {
        return model.getItemsByGenre(genreId);
    }

    public void addItem(Item item) throws RemoteException, LibraryException {
        model.addItem(item);
    }

    public void deleteItem(int itemId) throws RemoteException, LibraryException {
        model.deleteItem(itemId);
    }
}
