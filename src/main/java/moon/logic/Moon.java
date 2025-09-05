package moon.logic;

import moon.commands.BaseCommand;
import moon.exceptions.MoonException;
import moon.models.TaskList;
import moon.parser.base.UserInputParser;
import moon.storage.Storage;
import moon.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

// Moon contains all core features of the chatbot
// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private TaskList taskList;  // list of task
    private Storage storage;
    private final Ui ui;

    // Constructor
    public Moon(String filepath) {
        Scanner myScanner = new Scanner(System.in);
        this.ui = new Ui(myScanner);
        initiateStorage(filepath);
    }

    public void initiateStorage(String filepath) {
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

    // function start the chatbot
    public void run() {
        ui.showGreetingMessage();
        ui.showLines();

        keepRunUntilExitMessage();

        ui.showExitMessage();
        ui.showLines();
    }

    public void keepRunUntilExitMessage() {
        int statusCode = 1; // status code 1 is default for running
        while (statusCode != 0) {
            try {
                ui.showAskingMessage();
                String userInput = ui.scan();
                ui.showLines();

                BaseCommand c = UserInputParser.parse(userInput);
                c.setMetaData(this.taskList, this.ui);
                statusCode = c.execute();
                this.storage.rewrite(this.taskList);

            } catch (MoonException e) {
                ui.showExceptionMessage(e.getMessage());
            } catch (IOException e) {
                ui.showIOExceptionMessage();
            } finally {
                ui.showLines();
            }
        }
    }
}