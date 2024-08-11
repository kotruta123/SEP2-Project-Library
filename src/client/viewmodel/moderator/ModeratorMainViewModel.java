package client.viewmodel.moderator;

import client.NavigationHelper;
import client.model.ClientModel;
import javafx.stage.Stage;

import java.io.IOException;

public class ModeratorMainViewModel {
    private final ClientModel model;
    private final NavigationHelper navigationHelper;

    public ModeratorMainViewModel(ClientModel model, NavigationHelper navigationHelper) {
        this.model = model;
        this.navigationHelper = navigationHelper;
    }

    public void showManageUsers(Stage stage) {
        navigationHelper.showManageUsers(stage);
    }

    public void showManageGenres(Stage stage) {
        navigationHelper.showManageGenres(stage);
    }

    public void showManageItems(Stage stage) {
        navigationHelper.showManageItems(stage);
    }

    public void logout(Stage primaryStage) {
        try {
            navigationHelper.showLogin(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
