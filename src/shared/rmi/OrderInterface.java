package shared.rmi;

import shared.model.Order;
import shared.model.OrderItem;
import shared.rmi.LibraryException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderInterface extends Remote {
    void placeOrder(Order order) throws RemoteException, LibraryException;
    List<Order> getOrdersByUser(int userId) throws RemoteException, LibraryException;
    List<OrderItem> getOrderItems(int orderId) throws RemoteException, LibraryException;
}
