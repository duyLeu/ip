package moon.logic;

import moon.commands.BaseCommand;
import moon.exceptions.MoonException;

import java.io.IOException;

import java.util.Scanner;

import moon.models.*;

import moon.ui.Ui;
import moon.storage.Storage;


// Moon contains all core features of the chatbot
// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private TaskList taskList;  // list of task
    private Storage storage;
    private Ui ui;

    // Constructor
    public Moon(String filepath) {
        initiateStorage(filepath);
        this.ui = new Ui();
    }

    public void initiateStorage(String filepath) {
        try {
            this.storage = new Storage(filepath);
            taskList = storage.load();
        } catch (IOException | MoonException e) {
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Moon chatbot = new Moon("src/data/storage.txt");
        chatbot.run();
    }

    // function start the chatbot
    public void run() {
        ui.showGreetingMessage();
        keepRunUntilExitMessage();
    }

    // handle the string input
    public int handleInput(String input) {

    }


    public void keepRunUntilExitMessage() {
        Scanner myScanner = new Scanner(System.in);
        int statusCode = 1; // status code 1 is default for running
        while (statusCode != 0) {
            try {
                ui.showAskingMessage();
                String userInput = ui.scan(myScanner);
                ui.showLines();
                BaseCommand c = Parser.parseFromInput(userInput);
                c.setMetaData(this.taskList, this.ui);
                statusCode = c.execute();
                this.storage.rewrite(this.taskList);
            } catch (IOException e) {
                System.out.println("Error loading into storage.");
            } finally {
                ui.showLines();
            }
        }
    }
}