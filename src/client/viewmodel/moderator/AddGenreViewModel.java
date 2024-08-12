package client.viewmodel.moderator;

import client.model.ClientModel;
import shared.model.Genre;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AddGenreViewModel {
    private final ClientModel model;

    public AddGenreViewModel(ClientModel model) {
        this.model = model;
    }

    public void addGenre(String name, File imageFile) {
        try {
            Path sourceDirectory = imageFile.toPath().getParent();

            Path targetDirectory = Path.of("C:/Users/Alexandru/Desktop/SEP2-Project-Library/others/server_images");

            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }

            Path targetPath = targetDirectory.resolve(imageFile.getName());

            Files.copy(imageFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Genre genre = new Genre(0, name, targetPath.toString());
            model.addGenre(genre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
