package server;

import server.db.DatabaseConnection;
import server.db.GenreDAO;
import server.db.ItemDAO;
import server.db.UserDAO;
import server.db.OrderDAO; // Add this import
import server.service.GenreServiceImpl;
import server.service.ItemServiceImpl;
import server.service.UserServiceImpl;
import server.service.OrderServiceImpl; // Add this import
import shared.rmi.GenreInterface;
import shared.rmi.ItemInterface;
import shared.rmi.UserInterface;
import shared.rmi.OrderInterface; // Add this import

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LibraryServer {
    public static void main(String[] args) {
        try {
            DatabaseConnection.initialize();

            GenreDAO genreDAO = new GenreDAO(DatabaseConnection.getConnection());
            UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection());
            ItemDAO itemDAO = new ItemDAO(DatabaseConnection.getConnection());
            OrderDAO orderDAO = new OrderDAO(DatabaseConnection.getConnection()); // Add this

            GenreServiceImpl genreService = new GenreServiceImpl(genreDAO);
            UserServiceImpl userService = new UserServiceImpl(userDAO);
            ItemServiceImpl itemService = new ItemServiceImpl(itemDAO);
            OrderServiceImpl orderService = new OrderServiceImpl(orderDAO); // Add this

            GenreInterface genreStub = (GenreInterface) UnicastRemoteObject.exportObject(genreService, 0);
            UserInterface userStub = (UserInterface) UnicastRemoteObject.exportObject(userService, 0);
            ItemInterface itemStub = (ItemInterface) UnicastRemoteObject.exportObject(itemService, 0);
            OrderInterface orderStub = (OrderInterface) UnicastRemoteObject.exportObject(orderService, 0); // Add this

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GenreService", genreStub);
            registry.rebind("UserService", userStub);
            registry.rebind("ItemService", itemStub);
            registry.rebind("OrderService", orderStub); // Add this

            System.out.println("Library server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
