package shared.model;

import java.io.Serializable;

public class Genre implements Serializable {
    private int genreId;
    private String name;
    private String image;

    public Genre(int genreId, String name, String image) {
        this.genreId = genreId;
        this.name = name;
        this.image = image;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

}
