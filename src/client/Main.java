package client;

import client.model.ClientModel;
import javafx.application.Application;
import javafx.stage.Stage;
import shared.rmi.GenreInterface;
import shared.rmi.ItemInterface;
import shared.rmi.OrderInterface;
import shared.rmi.UserInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            UserInterface userService = (UserInterface) registry.lookup("UserService");
            GenreInterface genreService = (GenreInterface) registry.lookup("GenreService");
            ItemInterface itemService = (ItemInterface) registry.lookup("ItemService");
            OrderInterface orderService = (OrderInterface) registry.lookup("OrderService");

            ClientModel clientModel = new ClientModel(userService, genreService, itemService, orderService);
            NavigationHelper navigationHelper = new NavigationHelper(primaryStage, clientModel);
            navigationHelper.showLogin(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
