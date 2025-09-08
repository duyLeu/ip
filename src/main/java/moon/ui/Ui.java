package moon.ui;

import moon.models.Task;
import moon.models.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    public String scan() {
        return sc.nextLine();
    }

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

    public void showExceptionMessage(String s) {
        System.out.println("\tMoon: " + s);
    }

    public void showIOExceptionMessage() {
        showExceptionMessage("Sorryyy! I incurred some error while trying to add this task. "
                + "Would you mind trying again? Woof!");
    }

    public void showLoadStorageSuccessfulMessage(TaskList list) {
        System.out.printf("\tMoon: I have retrieved your previous task list!\n%s\t\t  Woof!", list);
    }

    public void showLoadStorageUnsuccessfulMessage() {
        System.out.println("\tMoon: I couldn't retrieve your previous task. No worries! Let's start again! Woof!");
    }

    public void showEmptyInitialStorageMessage() {
        System.out.println("\tMoon: Time to start tasking! A-wooooo!");
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
        System.out.printf("\tMoon: Here are the items in your list!\n%s\t\t  Woof!\n", list);
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

    public void showTasksMatchedMessage(TaskList matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            System.out.printf("\tMoon: I can't find any tasks matching your keyword: %s . Wuf!\n", keyword);
        } else {
            System.out.printf("\tMoon: I found these tasks matching your keyword!\n%s\t\t  Woof!\n", matchedTasks);
        }
    }
}
