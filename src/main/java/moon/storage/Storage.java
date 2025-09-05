package moon.storage;

import moon.models.TaskList;
import moon.parser.exceptions.ParseException;
import moon.parser.storage.AddFromStorageParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path storageFile;

    public Storage(String filepath) throws IOException {
        this.storageFile = Paths.get(filepath);
        initialiseStorageIfNotExist();
    }

    public void initialiseStorageIfNotExist() throws IOException {
        // Ensure parent folder exists
        if (this.storageFile.getParent() != null) {
            Files.createDirectories(this.storageFile.getParent());
        }

        // If the file doesn’t exist, create an empty one
        if (Files.notExists(this.storageFile)) {
            Files.createFile(this.storageFile);
        }
    }

    public TaskList load() throws IOException, ParseException {
        initialiseStorageIfNotExist();
        List<String> lines = Files.readAllLines(storageFile);
        TaskList tasks = new TaskList();
        for (String line : lines) {
            if (!line.isBlank()) {
                tasks.add(AddFromStorageParser.parse(line));
            }
        }
        return tasks;
    }

    public void rewrite(TaskList tasks) throws IOException {
        initialiseStorageIfNotExist();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(tasks.get(i).toStorageString());
        }
        Files.write(this.storageFile, lines,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
