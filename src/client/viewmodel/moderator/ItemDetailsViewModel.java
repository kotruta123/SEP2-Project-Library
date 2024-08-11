package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.model.Item;

public class ItemDetailsViewModel {
    private final ClientModel model;
    private final Item item;
    private final StringProperty itemDetails;

    public ItemDetailsViewModel(ClientModel model, Item item) {
        this.model = model;
        this.item = item;
        this.itemDetails = new SimpleStringProperty(formatItemDetails(item));
    }

    public StringProperty itemDetailsProperty() {
        return itemDetails;
    }

    private String formatItemDetails(Item item) {
        return "ID: " + item.getItemId() + "\n" +
                "Title: " + item.getTitle() + "\n" +
                "Author: " + item.getAuthor() + "\n" +
                "Price: " + item.getPrice() + "\n" +
                "Description: " + item.getDescription() + "\n" +
                "Image: " + item.getImage() + "\n" +
                "Type: " + item.getType() + "\n" +
                "Genre: " + item.getGenre().getName();
    }
}
