package logic;

import command.Command;
import exceptions.MoonException;

import java.io.IOException;

import java.util.Scanner;

import models.*;

import util.Storage;


// Moon contains all core features of the chatbot
// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private TaskList taskList;  // list of task
    private Storage storage;

    // Constructor
    public Moon(String filepath) {
        try {
            this.storage = new Storage(filepath);
            taskList = storage.load();
        } catch (IOException | MoonException e) {
            taskList = new TaskList();
        }
    }

    // ===== Default Messages Methods =====

    // display greeting message
    public void getGreetingMessage() {
        String logo =
                """
                         __  __   ____    ____    _   _
                        |  \\/  | / __ \\  / __ \\  | \\ | |     __      _
                        | |\\/| || |  | || | () | |  \\| |   o'')}____//
                        | |  | || |__| || @ . o| | |\\  |    `_/      )
                        |_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/
                        """;
        System.out.printf("%s\tMoon: Woof woof! I'm Moon! Your personal assistant! 🐾\n", logo);
    }

    // display goodbye message when exit the bot
    public void getExitMessage() {
        System.out.println("\tMoon: Byeee! Hope to see you again! A-woooooooooooo! 🐾");
    }

    // display prompting message when the chatbot wants to ask
    public void getAskingMessage() {
        System.out.println("\tMoon: How may I help you?");
    }

    // print out the horizontal lines top and bottom of an answer
    public void getHorizontalLines() {
        System.out.println("\t____________________________________________________________");
    }



    // handle the string input
    public int handleInput(String input) {
        try {
            Command command = Command.findMatchingCommand(input);
            // switch-case structure for better readability
            switch (command) {
                case EXIT -> getExitMessage();
                case LIST -> getTaskList();
                case MARK -> setTaskDone(input, true);
                case UNMARK -> setTaskDone(input, false);
                case TODO -> addTask(Todo.parseFromInput(input));
                case DEADLINE -> addTask(Deadline.parseFromInput(input));
                case EVENT -> addTask(Event.parseFromInput(input));
                case DELETE -> deleteTask(input);
            }
            return command.getStatusCode();

        } catch (MoonException e) { // catch any known exception
            System.out.println(e.getMessage());
            return -1;

        } catch (RuntimeException e) { // in case of unexpected behaviour, the program will not crash
            System.out.println(e);
            System.out.println("\tMoon: Wuf? I don't understand that, can you try again?");
            return -1;
        }
    }

    // ===== Main running method =====

    // function start the chatbot
    public void run() {
        this.getGreetingMessage();

        // create a Scanner object to scan the next line
        Scanner myScanner = new Scanner(System.in);

        // the loop keep prompting the user until it receive the string "bye", which returns status code 0
        int statusCode = 1; // status code 1 is default for running
        while (statusCode != 0) {
            try {
                this.getAskingMessage();
                // scan user input
                String userInput = myScanner.nextLine();
                // process user input, while returning a status code
                this.getHorizontalLines();
                statusCode = this.handleInput(userInput);
                this.storage.save(this.taskList);
            } catch (IOException e) {
                System.out.println("Error loading into storage.");
            } finally {
                this.getHorizontalLines();
            }
        }
    }
}