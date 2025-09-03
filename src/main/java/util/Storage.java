package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final Path file;

    public Storage(String filepath) throws IOException {
        this.file = Paths.get(filepath);
        initialiseStorage();
    }

    public void initialiseStorage() throws IOException {
        // Ensure parent folder exists
        if (this.file.getParent() != null) {
            Files.createDirectories(this.file.getParent());
        }

        // If the file doesn’t exist, create an empty one
        if (Files.notExists(this.file)) {
            Files.createFile(this.file);
        }
    }


}
