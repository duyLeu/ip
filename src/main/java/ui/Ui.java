package ui;

import exceptions.InvalidIndexException;
import command.Command;
import models.Task;

public class Ui {
    public Ui() {

    }

    // ===== Default Messages Methods =====

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

    // ===== Task Management Methods =====

    // display the task list
    public void getTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("\tMoon: You haven't added anything to your list yet. Time to start tasking! A-wooooo!");
        } else {
            System.out.printf("\tMoon: Here are the items in your list!\n%s\t\t  Woof!", this.taskList);
        }
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

            Task removedTask = taskList.delete(taskIndex);
            System.out.printf("\tMoon: Copy that! I've deleted this task! Woof!\n\t\t\t%s\n",
                    removedTask);

        } catch (NumberFormatException e) { // exception thrown by parseInt()
            throw new InvalidIndexException(Command.DELETE,
                    "Wuf! I don't think you put in an integer?");
        }
    }

    // mark/unmark the task based on the isDone argument.
    public void setTaskDone(String input, boolean setDone) throws InvalidIndexException {
        try {
            // the first line splits the input string then check for the *second* element for the list index
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) { // check for index range
                throw new InvalidIndexException(Command.DELETE,
                        "Wuf! Your index is out of range!");
            }

            Task taskToSet = taskList.get(taskIndex);

            if (taskToSet.isDone() == setDone) {
                // in case task is already marked
                System.out.printf("\tMoon: I see you have already marked/unmarked this task!\n\t\t\t%s\n",
                        taskToSet);

            } else if (setDone) {
                taskToSet.setDone();
                System.out.printf("\tMoon: Nicely done! I've pawed this as done! Woof!\n\t\t\t%s\n",
                        taskToSet);

            } else {
                taskToSet.setNotDone();
                System.out.printf("\tMoon: No worries! I've pawed this as not done! You can do it! Woof!\n\t\t\t%s\n",
                        taskToSet);

            }
        } catch (NumberFormatException e) { // exception thrown by parseInt()
            Command commandType = setDone ? Command.MARK : Command.UNMARK;
            throw new InvalidIndexException(commandType,
                    "Wuf! I don't think you put in an integer?");
        }
    }
}
