package moon.logic;

import java.io.IOException;
import java.util.NoSuchElementException;

import moon.commands.BaseCommand;
import moon.logic.exceptions.MoonException;
import moon.models.TaskList;
import moon.parser.usercommand.UserInputParser;
import moon.storage.Storage;
import moon.ui.UiMessages;

/**
 * Main logic class of the Moon chatbot.
 * <p>
 * Passes data between the UI, storage, and the commands.
 */
public class Moon {
    private static final String DEFAULT_FILEPATH = "src/data/storage.txt";
    protected final UiMessages uiMessages;
    private final String filepath;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor. Creates a new Moon chatbot with the given storage file path.
     *
     * @param filepath Path to the storage file
     */
    public Moon(String filepath) {
        this.uiMessages = new UiMessages();
        this.filepath = filepath;
    }

    public Moon() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return processUserInput(input);
    }

    /**
     * Initialises the storage and loads tasks from file.
     * If loading fails, starts with an empty task list.
     */
    public String initiateStorage() {
        try {
            this.storage = new Storage(filepath);
            taskList = storage.load();

            if (taskList.isEmpty()) {
                return uiMessages.showEmptyInitialStorageMessage();
            } else {
                return uiMessages.showLoadStorageSuccessfulMessage(this.taskList);
            }
        } catch (IOException | MoonException e) {
            taskList = new TaskList();
            return uiMessages.showLoadStorageUnsuccessfulMessage();
        }
    }

    public String processUserInput(String userInput) {
        try {
            // parse the user input, then send the meta-data, including the task list
            // and the UI, execute the command, which will return a status code.
            // Then rewrite the storage.txt file using the new, modified task list
            // (Not the most efficient solution, but for small amount of data, it is acceptable)
            BaseCommand c = UserInputParser.parse(userInput);
            c.setMetaData(this.taskList, this.uiMessages);
            String response = c.execute();
            this.storage.rewrite(this.taskList);

            return response;
        } catch (MoonException e) { // exceptions returned by parser/commands
            return uiMessages.showExceptionMessage(e.getMessage());
        } catch (NoSuchElementException | IOException e) { // exceptions returned by scanner or storage
            return uiMessages.showGeneralErrorMessage();
        }
    }

    public boolean isExitCommand(String message) {
        return message.equalsIgnoreCase("bye");
    }
}
