package server.service;

import shared.model.Item;
import shared.rmi.ItemInterface;
import shared.rmi.LibraryException;
import server.db.ItemDAO;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class ItemServiceImpl implements ItemInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;

    public ItemServiceImpl(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Item> getItemsByGenre(int genreId) throws RemoteException, LibraryException {
        return itemDAO.getItemsByGenre(genreId);
    }

    @Override
    public void addItem(Item item) throws RemoteException, LibraryException {
        itemDAO.addItem(item);
    }
    @Override
    public void updateItem(Item item) throws RemoteException, LibraryException {
        itemDAO.updateItem(item);
    }


    @Override
    public void deleteItem(int itemId) throws RemoteException, LibraryException {
        itemDAO.deleteItem(itemId);
    }

    @Override
    public List<Item> getAllItems() throws RemoteException, LibraryException {
        return itemDAO.getAllItems();
    }
}
