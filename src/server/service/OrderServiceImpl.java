package server.service;

import server.db.OrderDAO;
import shared.model.Order;
import shared.model.OrderItem;
import shared.rmi.OrderInterface;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;
import java.util.List;

public class OrderServiceImpl implements OrderInterface {
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void placeOrder(Order order) throws RemoteException, LibraryException {
        orderDAO.placeOrder(order);
    }

    @Override
    public List<Order> getOrdersByUser(int userId) throws RemoteException, LibraryException {
        return orderDAO.getOrdersByUser(userId);
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId) throws RemoteException, LibraryException {
        return orderDAO.getOrderItems(orderId);
    }
}
