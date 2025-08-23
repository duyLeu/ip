package app;

import exception.InvalidIndexException;
import exception.MoonException;
import java.util.Scanner;
import java.util.ArrayList;

// Moon contains all core features of the chatbot
// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private final ArrayList<Task> taskList;  // list of task, ArrayList is more convenient for add/get/delete items

    // Constructor
    public Moon() {
        this.taskList = new ArrayList<>();
    }

    // ===== Default Messages Methods =====

    // display greeting message
    public void getGreetingMessage() {
        String logo =
                """
                         __  __   ____    ____    _   _\s
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

    // ===== Task Management Methods =====

    // display the task list
    public void getTaskList() {
        System.out.println("\tMoon: Here are the items in your list!");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t\t  %d. %s\n", i + 1, taskList.get(i));
        }
        System.out.println("\t\t  Woof!");
    }

    // add task into the list
    public void addTask(Task task) {
        taskList.add(task);
        System.out.printf("\tMoon: Copy that! I've added this task!\n\t\t\t%s\n", task);
    }

    // delete task
    public void deleteTask(String input) throws InvalidIndexException {
        try {
            // the first line splits the input string then check for the *second* element for the list index
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) { // check for index range
                throw new InvalidIndexException(Command.DELETE,
                        "Wuf! Your index is out of range!");
            }

            Task removedTask = taskList.remove(taskIndex);
            System.out.printf("\tMoon: Copy that! I've deleted this task! Woof!\n\t\t\t%s\n",
                    removedTask);

        } catch (NumberFormatException e) { // exception thrown by parseInt()
            throw new InvalidIndexException(Command.DELETE,
                    "Wuf! I can't recognize your index. Are you sure it's an integer?");
        }
    }

    // mark/unmark the task based on the isDone argument.
    public void setTaskDone(String input, boolean isDone) throws InvalidIndexException {
        try {
            // the first line splits the input string then check for the *second* element for the list index
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) { // check for index range
                throw new InvalidIndexException(Command.DELETE,
                        "Wuf! Your index is out of range!");
            }

            Task taskToSet = taskList.get(taskIndex);
            if (isDone) {
                taskToSet.setDone();
                System.out.printf("\tMoon: Nicely done! I've pawed this as done! Woof!\n\t\t\t%s\n",
                        taskToSet);
            } else {
                taskToSet.setNotDone();
                System.out.printf("\tMoon: No worries! I've pawed this as not done! You can do it! Woof!\n\t\t\t%s\n",
                        taskToSet);
            }
        } catch (NumberFormatException e) { // exception thrown by parseInt()
            Command commandType = isDone ? Command.MARK : Command.UNMARK;
            throw new InvalidIndexException(commandType,
                    "Wuf! I can't recognize your index. Are you sure it's an integer?");
        }
    }

    // handle the string input
    public int handleInput(String input) {
        try {
            Command command = Command.fromString(input);
            switch (command) {
                case EXIT -> getExitMessage();
                case LIST -> getTaskList();
                case MARK -> setTaskDone(input, true);
                case UNMARK -> setTaskDone(input, false);
                case TODO -> addTask(Todo.fromString(input));
                case DEADLINE -> addTask(Deadline.fromString(input));
                case EVENT -> addTask(Event.fromString(input));
                case DELETE -> deleteTask(input);
            }
            return command.getStatusCode();

        } catch (MoonException e) { // catch any known exception
            System.out.println(e.getMessage());
            return -1;

        } catch (RuntimeException e) { // in case of unexpected behaviour, the program will not crash
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

            this.getAskingMessage();
            // scan user input
            String userInput = myScanner.nextLine();
            // process user input, while returning a status code
            this.getHorizontalLines();
            statusCode = this.handleInput(userInput);
            this.getHorizontalLines();
        }
    }
}