package moon.logic;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        Scanner myScanner = new Scanner(System.in);
        this.uiMessages = new UiMessages(myScanner);
        this.filepath = filepath;
    }

    public Moon() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {

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

    /**
     * Starts the chatbot by showing a greeting,
     * initialising storage, and entering the main loop.
     */
    public void run() {
        uiMessages.showGreetingMessage();
        initiateStorage();
        keepRunUntilExitCommand();
    }

    public String processUserInput(String userInput) {
        try {
            // parse the user input, then send the meta-data, including the task list
            // and the UI, execute the command, which will return a status code.
            // Then rewrite the storage.txt file using the new, modified task list
            // (Not the most efficient solution, but for small amount of data, it is acceptable)
            BaseCommand c = UserInputParser.parse(userInput);
            c.setMetaData(this.taskList, this.uiMessages);
            int statusCode = c.execute();
            this.storage.rewrite(this.taskList);

            if (statusCode != -1) { // status code -1: error
                uiMessages.showLines();
                uiMessages.showAskingMessage();
            }

        } catch (MoonException e) { // exceptions returned by parser/commands
            uiMessages.showExceptionMessage(e.getMessage());
            statusCode = -1;
        } catch (NoSuchElementException | IOException e) { // exceptions returned by scanner or storage
            uiMessages.showGeneralErrorMessage();
            statusCode = -1;
        }
    }

    /**
     * Runs the main input-processing loop until the user uses the exit command.
     */
    public void keepRunUntilExitCommand() {
        int statusCode = 1; // status code 1: running
        while (statusCode != 0) { // status code 0: exit
            try {
                if (statusCode != -1) { // status code -1: error
                    uiMessages.showLines();
                    uiMessages.showAskingMessage();
                }

                String userInput = uiMessages.scan();

                // parse the user input, then send the meta-data, including the task list
                // and the UI, execute the command, which will return a status code.
                // Then rewrite the storage.txt file using the new, modified task list
                // (Not the most efficient solution, but for small amount of data, it is acceptable)
                BaseCommand c = UserInputParser.parse(userInput);
                c.setMetaData(this.taskList, this.uiMessages);
                statusCode = c.execute();
                this.storage.rewrite(this.taskList);

            } catch (MoonException e) { // exceptions returned by parser/commands
                uiMessages.showExceptionMessage(e.getMessage());
                statusCode = -1;
            } catch (NoSuchElementException | IOException e) { // exceptions returned by scanner or storage
                uiMessages.showGeneralErrorMessage();
                statusCode = -1;
            }
        }
    }
}
