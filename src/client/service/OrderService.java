package client.service;

import client.model.ClientModel;
import shared.model.Order;
import shared.model.OrderItem;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class OrderService {
    private ClientModel model;

    public OrderService(ClientModel model) {
        this.model = model;
    }

    public void placeOrder(Order order) throws RemoteException, LibraryException {
        model.placeOrder(order);
    }

    public List<Order> getOrdersByUser(int userId) throws RemoteException, LibraryException {
        return model.getOrdersByUser(userId);
    }

    public List<OrderItem> getOrderItems(int orderId) throws RemoteException, LibraryException {
        return model.getOrderItems(orderId);
    }
}
