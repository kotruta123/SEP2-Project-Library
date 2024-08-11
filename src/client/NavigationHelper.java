package client;

import client.model.ClientModel;
import client.view.controller.*;
import client.view.controller.customer.*;
import client.view.controller.moderator.*;
import client.viewmodel.*;
import client.viewmodel.customer.*;
import client.viewmodel.moderator.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.model.Genre;
import shared.model.Item;
import shared.model.User;
import shared.rmi.LibraryException;

import java.io.IOException;
import java.util.Stack;

public class NavigationHelper {
    private final Stage primaryStage;
    private final ClientModel clientModel;
    private final Stack<Runnable> viewStack = new Stack<>();

    public NavigationHelper(Stage primaryStage, ClientModel clientModel) {
        this.primaryStage = primaryStage;
        this.clientModel = clientModel;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void goBack(Stage primaryStage) {
        if (!viewStack.isEmpty()) {
            viewStack.pop().run();
        }
    }

    public void showLogin(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainViewController controller = loader.getController();
        controller.init(new MainViewModel(clientModel, this), this.primaryStage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void showRegister(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/RegisterView.fxml"));
        Scene scene = new Scene(loader.load());
        RegisterViewController controller = loader.getController();
        controller.init(new RegisterViewModel(clientModel.getUserService(), this), this.primaryStage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void setCurrentUser(User user) {
        clientModel.setCurrentUser(user);
    }

    public void showCustomerView(Stage stage, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/CustomerView.fxml"));
            Scene scene = new Scene(loader.load());
            CustomerViewController controller = loader.getController();
            CustomerViewModel viewModel = new CustomerViewModel(clientModel, this);
            controller.init(viewModel, stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showItemTypeSelectionView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ItemTypeSelectionView.fxml"));
            Scene scene = new Scene(loader.load());
            ItemTypeSelectionViewController controller = loader.getController();
            ItemTypeSelectionViewModel viewModel = new ItemTypeSelectionViewModel(clientModel, this);
            controller.init(viewModel, stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGenreSelectionView(Stage stage, String itemType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/GenreSelectionView.fxml"));
            Scene scene = new Scene(loader.load());
            GenreSelectionViewController controller = loader.getController();
            GenreSelectionViewModel viewModel = new GenreSelectionViewModel(clientModel, this);
            controller.init(viewModel,this ,stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showItemListView(Stage stage, Genre genre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ItemListView.fxml"));
            Scene scene = new Scene(loader.load());
            ItemListViewController controller = loader.getController();
            ItemListViewModel viewModel = new ItemListViewModel(clientModel, genre, this);
            controller.init(viewModel, stage, this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException | LibraryException e) {
            e.printStackTrace();
        }
    }

    public void showItemDetailView(Stage stage, Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ItemDetailView.fxml"));
            Scene scene = new Scene(loader.load());
            ItemDetailViewController controller = loader.getController();
            ItemDetailViewModel viewModel = new ItemDetailViewModel(clientModel, item);
            controller.init(viewModel,this ,stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCartView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/CartView.fxml"));
            Scene scene = new Scene(loader.load());
            CartViewController controller = loader.getController();
            CartViewModel viewModel = new CartViewModel(clientModel);
            controller.init(viewModel, stage, this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrderConfirmation(Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/OrderConfirmationView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Order Confirmation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            OrderConfirmationViewController controller = loader.getController();
            controller.setStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showModeratorMainView(Stage stage, User user) {
        try {
            viewStack.push(() -> {
                try {
                    showLogin(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ModeratorMainView.fxml"));
            Scene scene = new Scene(loader.load());
            ModeratorMainViewController controller = loader.getController();
            ModeratorMainViewModel viewModel = new ModeratorMainViewModel(clientModel, this);
            controller.init(viewModel, stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showManageItems(Stage stage) {
        try {
            viewStack.push(() -> showModeratorMainView(stage, null));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ManageItemsView.fxml"));
            Scene scene = new Scene(loader.load());
            ManageItemsViewController controller = loader.getController();
            ManageItemsViewModel viewModel = new ManageItemsViewModel(clientModel, this);
            controller.init(viewModel, stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEditItemView(Item item, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/EditItemView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            EditItemViewController controller = loader.getController();
            EditItemViewModel viewModel = new EditItemViewModel(clientModel, item);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showManageGenres(Stage stage) {
        try {
            viewStack.push(() -> showModeratorMainView(stage, null));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ManageGenresView.fxml"));
            Scene scene = new Scene(loader.load());
            ManageGenresViewController controller = loader.getController();
            ManageGenresViewModel viewModel = new ManageGenresViewModel(clientModel, this);
            controller.init(viewModel, stage, this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEditGenreView(Genre genre, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/EditGenreView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Genre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            EditGenreViewController controller = loader.getController();
            ManageGenresViewModel manageGenresViewModel = new ManageGenresViewModel(clientModel, this);
            EditGenreViewModel viewModel = new EditGenreViewModel(clientModel, manageGenresViewModel, genre);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showManageUsers(Stage stage) {
        try {
            viewStack.push(() -> showModeratorMainView(stage, null));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ManageUsersView.fxml"));
            Scene scene = new Scene(loader.load());
            ManageUsersViewController controller = loader.getController();
            ManageUsersViewModel viewModel = new ManageUsersViewModel(clientModel, this);
            controller.init(viewModel, stage, this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserDetails(User user, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/UserDetailsView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("User Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            UserDetailsViewController controller = loader.getController();
            UserDetailsViewModel viewModel = new UserDetailsViewModel(clientModel, user);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGenreDetails(Genre genre, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/GenreDetailsView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Genre Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            GenreDetailsViewController controller = loader.getController();
            GenreDetailsViewModel viewModel = new GenreDetailsViewModel(clientModel, genre);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddGenreView(Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/AddGenreView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Genre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            AddGenreViewController controller = loader.getController();
            AddGenreViewModel viewModel = new AddGenreViewModel(clientModel);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddItemView(Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/AddItemView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            AddItemViewController controller = loader.getController();
            AddItemViewModel viewModel = new AddItemViewModel(clientModel);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showItemDetails(Item item, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ItemDetailsView.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Item Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(loader.load()));

            ItemDetailsViewController controller = loader.getController();
            ItemDetailsViewModel viewModel = new ItemDetailsViewModel(clientModel, item);
            controller.init(viewModel, dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
