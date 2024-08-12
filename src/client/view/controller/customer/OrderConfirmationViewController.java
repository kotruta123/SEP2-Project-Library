package client.view.controller.customer;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class OrderConfirmationViewController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleOk() {
        stage.close();
    }
}
