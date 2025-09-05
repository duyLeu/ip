package moon.ui;

import moon.parser.exceptions.InvalidIndexException;
import moon.commands.enums.Command;
import moon.models.Task;
import moon.models.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    public void showGreetingMessage() {
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
    public void showExitMessage() {
        System.out.println("\tMoon: Byeee! Hope to see you again! A-woooooooooooo! 🐾");
    }

    // display prompting message when the chatbot wants to ask
    public void showAskingMessage() {
        System.out.println("\tMoon: How may I help you?");
    }

    // print out the horizontal lines top and bottom of an answer
    public void showLines() {
        System.out.println("\t____________________________________________________________");
    }

    public void showErrorMessage(String s) {
        System.out.println("\t" + s);
    }

    public void showAddTaskMessage(Task addedTask) {
        System.out.printf("\tMoon: Copy that! I've added this task!\n\t\t\t%s\n",
                addedTask);
    }

    public void showDeleteTaskMessage(Task deletedTask) {
        System.out.printf("\tMoon: Copy that! I've deleted this task! Woof!\n\t\t\t%s\n",
                deletedTask);
    }

    public void showEmptyListMessage() {
        System.out.println("\tMoon: You haven't added anything to your list yet. Time to start tasking! A-wooooo!");
    }

    public void showListMessage(TaskList list) {
        System.out.printf("\tMoon: Here are the items in your list!\n%s\t\t  Woof!", list);
    }

    public void showAlreadyMarkedMessage(Task alreadyMarkedTask) {
        System.out.printf("\tMoon: I see you have already marked this task!\n\t\t\t%s\n",
                alreadyMarkedTask);
    }

    public void showAlreadyUnmarkedMessage(Task alreadyUnmarkedTask) {
        System.out.printf("\tMoon: I see you have already unmarked this task!\n\t\t\t%s\n",
                alreadyUnmarkedTask);
    }

    public void showMarkedSuccessfulMessage(Task markedTask) {
        System.out.printf("\tMoon: Nicely done! I've pawed this as done! Woof!\n\t\t\t%s\n",
                markedTask);
    }

    public void showUnmarkedSuccessfulMessage(Task unmarkedTask) {
        System.out.printf("\tMoon: No worries! I've pawed this as not done! You can do it! Woof!\n\t\t\t%s\n",
                unmarkedTask);
    }

    public String scan(Scanner sc) throws IOException {
        return sc.nextLine();
    }



    // mark/unmark the task based on the isDone argument.
    public void setTaskDone(String input, boolean setDone) throws InvalidIndexException {
        try {

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
