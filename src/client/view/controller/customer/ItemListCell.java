package client.view.controller.customer;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.model.Item;

public class ItemListCell extends ListCell<Item> {
    private HBox content;
    private Text title;
    private Text author;
    private ImageView imageView;
    private Text description;

    public ItemListCell() {
        super();
        title = new Text();
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        author = new Text();
        author.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        description = new Text();
        description.setStyle("-fx-font-size: 12px; -fx-text-fill: #333; -fx-background-color: #fff; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-width: 1;");
        imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");

        VBox vBox = new VBox(title, author, description);
        vBox.setSpacing(5);
        HBox.setHgrow(vBox, Priority.ALWAYS);
        content = new HBox(imageView, vBox);
        content.setSpacing(10);
        content.setStyle("-fx-padding: 10; -fx-border-color: #cccccc; -fx-border-width: 1; -fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
    }

    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            title.setText(item.getTitle());
            author.setText(item.getAuthor());
            description.setText(item.getDescription());
            if (item.getImage() != null && !item.getImage().isEmpty()) {
                imageView.setImage(new Image("file:" + item.getImage()));
            } else {
                imageView.setImage(null);
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
