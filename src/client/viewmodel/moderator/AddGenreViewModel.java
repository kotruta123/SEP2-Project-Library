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
            // Define the source directory for the images
            Path sourceDirectory = imageFile.toPath().getParent();

            // Define the target directory
            Path targetDirectory = Path.of("C:/Users/Alexandru/Desktop/SEP2-Project-Library/others/server_images");

            // Ensure the target directory exists
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }

            // Define the target path for the file
            Path targetPath = targetDirectory.resolve(imageFile.getName());

            // Copy the file to the server's designated folder
            Files.copy(imageFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Create a Genre object with the relative path to the image
            Genre genre = new Genre(0, name, targetPath.toString());
            model.addGenre(genre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
