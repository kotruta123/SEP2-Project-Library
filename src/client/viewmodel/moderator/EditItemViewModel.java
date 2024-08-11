package client.viewmodel.moderator;

import client.model.ClientModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Item;
import shared.rmi.LibraryException;

import java.rmi.RemoteException;

public class EditItemViewModel {
    private final ClientModel clientModel;
    private final Item item;
    private final StringProperty title;
    private final StringProperty author;
    private final DoubleProperty price;
    private final StringProperty description;
    private final StringProperty image;
    private final StringProperty type;
    private final ObservableList<String> availableTypes;

    public EditItemViewModel(ClientModel clientModel, Item item) {
        this.clientModel = clientModel;
        this.item = item;
        this.title = new SimpleStringProperty(item.getTitle());
        this.author = new SimpleStringProperty(item.getAuthor());
        this.price = new SimpleDoubleProperty(item.getPrice());
        this.description = new SimpleStringProperty(item.getDescription());
        this.image = new SimpleStringProperty(item.getImage());
        this.type = new SimpleStringProperty(item.getType());
        this.availableTypes = FXCollections.observableArrayList("book", "magazine", "DVD"); // Example types
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

    public StringProperty typeProperty() {
        return type;
    }

    public ObservableList<String> getAvailableTypes() {
        return availableTypes;
    }

    public void save() {
        item.setTitle(title.get());
        item.setAuthor(author.get());
        item.setPrice(price.get());
        item.setDescription(description.get());
        item.setImage(image.get());
        item.setType(type.get());

        try {
            clientModel.updateItem(item);
        } catch (RemoteException | LibraryException e) {
            e.printStackTrace();
        }
    }
}
