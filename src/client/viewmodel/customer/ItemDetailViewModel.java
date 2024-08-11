package client.viewmodel.customer;

import client.model.ClientModel;
import javafx.beans.property.*;
import shared.model.Item;
import shared.model.OrderItem;

public class ItemDetailViewModel {
    private final ClientModel clientModel;
    private final Item item;

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty image = new SimpleStringProperty();

    public ItemDetailViewModel(ClientModel clientModel, Item item) {
        this.clientModel = clientModel;
        this.item = item;

        title.set(item.getTitle());
        author.set(item.getAuthor());
        price.set(item.getPrice());
        description.set(item.getDescription());
        image.set(item.getImage());
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void addToCart(int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        clientModel.addToCart(orderItem);
    }
}
