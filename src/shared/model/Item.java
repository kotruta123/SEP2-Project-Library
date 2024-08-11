package shared.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemId;
    private String title;
    private String author;
    private double price;
    private String description;
    private String image;
    private String type;
    private Genre genre;

    public Item(int itemId, String title, String author, double price, String description, String image, String type, Genre genre) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.image = image;
        this.type = type;
        this.genre = genre;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title;
    }
}
