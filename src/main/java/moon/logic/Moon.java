package moon.logic;

import moon.commands.BaseCommand;
import moon.logic.exceptions.MoonException;
import moon.models.TaskList;
import moon.parser.usercommand.UserInputParser;
import moon.storage.Storage;
import moon.ui.Ui;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main logic class of the Moon chatbot.
 * <p>
 * Passes data between the UI, storage, and the commands.
 */
public class Moon {
    protected final Ui ui;
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
        this.ui = new Ui(myScanner);
        this.filepath = filepath;
    }

    /**
     * Initialises the storage and loads tasks from file.
     * If loading fails, starts with an empty task list.
     */
    public void initiateStorage() {
        try {
            this.storage = new Storage(filepath);
            taskList = storage.load();

            if (taskList.isEmpty()) {
                ui.showEmptyInitialStorageMessage();
            } else {
                ui.showLoadStorageSuccessfulMessage(this.taskList);
            }
        } catch (IOException | MoonException e) {
            taskList = new TaskList();
            ui.showLoadStorageUnsuccessfulMessage();
        }
    }

    /**
     * Starts the chatbot by showing a greeting,
     * initialising storage, and entering the main loop.
     */
    public void run() {
        ui.showGreetingMessage();
        initiateStorage();
        keepRunUntilExitCommand();
    }

    /**
     * Runs the main input-processing loop until the user uses the exit command.
     */
    public void keepRunUntilExitCommand() {
        int statusCode = 1;              // status code 1: running
        while (statusCode != 0) {        // status code 0: exit
            try {
                if (statusCode != -1) {  // status code -1: error
                    ui.showLines();
                    ui.showAskingMessage();
                }

                String userInput = ui.scan();

                // parse the user input, then send the meta-data, including the task list
                // and the UI, execute the command, which will return a status code.
                // Then rewrite the storage.txt file using the new, modified task list
                // (Not the most efficient solution, but for small amount of data, it is acceptable)
                BaseCommand c = UserInputParser.parse(userInput);
                c.setMetaData(this.taskList, this.ui);
                statusCode = c.execute();
                this.storage.rewrite(this.taskList);

            } catch (MoonException e) {   // exceptions returned by parser/commands
                ui.showExceptionMessage(e.getMessage());
                statusCode = -1;
            } catch (NoSuchElementException | IOException e) {     // exceptions returned by scanner or storage
                ui.showIOExceptionMessage();
                statusCode = -1;
            }
        }
    }
}