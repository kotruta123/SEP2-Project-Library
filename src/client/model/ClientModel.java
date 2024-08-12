package client.model;

import shared.model.*;
import shared.rmi.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientModel {
    private UserInterface userService;
    private GenreInterface genreService;
    private ItemInterface itemService;
    private OrderInterface orderService;
    private User currentUser;
    private List<OrderItem> cartItems = new ArrayList<>();

    public ClientModel(UserInterface userService, GenreInterface genreService, ItemInterface itemService, OrderInterface orderService) {
        this.userService = userService;
        this.genreService = genreService;
        this.itemService = itemService;
        this.orderService = orderService;
        this.cartItems = new ArrayList<>();
    }

    public User login(String username, String password) throws RemoteException, LibraryException {
        currentUser = userService.login(username, password);
        return currentUser;
    }

    public void register(User user) throws RemoteException, LibraryException {
        userService.register(user);
    }

    public List<Genre> getAllGenres() throws RemoteException, LibraryException {
        return genreService.getAllGenres();
    }

    public void addGenre(Genre genre) throws RemoteException, LibraryException {
        genreService.addGenre(genre);
    }
    public void updateGenre(Genre genre) throws RemoteException, LibraryException {
        genreService.updateGenre(genre);
    }

    public void deleteGenre(int genreId) throws RemoteException, LibraryException {
        genreService.deleteGenre(genreId);
    }

    public List<Item> getItemsByGenre(int genreId) throws RemoteException, LibraryException {
        return itemService.getItemsByGenre(genreId);
    }

    public void addItem(Item item) throws RemoteException, LibraryException {
        itemService.addItem(item);
    }

    public List<Item> getAllItems() throws RemoteException, LibraryException {
        return itemService.getAllItems();
    }

    public void deleteItem(int itemId) throws RemoteException, LibraryException {
        itemService.deleteItem(itemId);
    }
    public void updateItem(Item item) throws RemoteException, LibraryException {
        itemService.updateItem(item);
    }


    public void placeOrder(Order order) throws RemoteException, LibraryException {
        orderService.placeOrder(order);
    }

    public List<Order> getOrdersByUser(int userId) throws RemoteException, LibraryException {
        return orderService.getOrdersByUser(userId);
    }

    public List<OrderItem> getOrderItems(int orderId) throws RemoteException, LibraryException {
        return orderService.getOrderItems(orderId);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getAllUsers() throws RemoteException, LibraryException {
        return userService.getAllUsers();
    }

    public void deleteUser(int userId) throws RemoteException, LibraryException {
        userService.deleteUser(userId);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public UserInterface getUserService() {
        return userService;
    }

    public GenreInterface getGenreService() {
        return genreService;
    }

    public ItemInterface getItemService() {
        return itemService;
    }

    public OrderInterface getOrderService() {
        return orderService;
    }

    public List<OrderItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(OrderItem orderItem) {
        for (OrderItem item : cartItems) {
            if (item.getItem().getItemId() == orderItem.getItem().getItemId()) {
                item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                return;
            }
        }
        cartItems.add(orderItem);
    }

    public void removeFromCart(OrderItem orderItem) {
        cartItems.remove(orderItem);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
