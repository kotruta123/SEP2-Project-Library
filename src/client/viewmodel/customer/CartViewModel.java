package client.viewmodel.customer;

import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Order;
import shared.model.OrderItem;
import shared.model.User;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class CartViewModel {
    private final ClientModel clientModel;
    private final ObservableList<OrderItem> cartItems;

    public CartViewModel(ClientModel clientModel) {
        this.clientModel = clientModel;
        this.cartItems = FXCollections.observableArrayList(clientModel.getCartItems());
    }

    public ObservableList<OrderItem> getCartItems() {
        return cartItems;
    }

    public void placeOrder() throws LibraryException, RemoteException {
        User currentUser = clientModel.getCurrentUser();
        Order order = new Order(currentUser, cartItems);
        clientModel.placeOrder(order);
        clientModel.clearCart();
        cartItems.clear();
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getItem().getPrice() * item.getQuantity())
                .sum();
    }

    public void removeFromCart(OrderItem orderItem) {
        cartItems.remove(orderItem);
        clientModel.removeFromCart(orderItem);
    }

    public void addToCart(OrderItem orderItem) {
        cartItems.add(orderItem);
    }
}
