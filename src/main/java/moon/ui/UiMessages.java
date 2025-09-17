package moon.ui;

import moon.models.Task;
import moon.models.TaskList;

public class UiMessages {

    public static String showGreetingMessage() {
        String logo =
                """
                         __  __   ____    ____    _   _
                        |  \\/  | / __ \\  / __ \\  | \\ | |     __      _
                        | |\\/| || |  | || | () | |  \\| |   o'')}____//
                        | |  | || |__| || @ . o| | |\\  |    `_/      )
                        |_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/
                        """;
        return String.format("%s\nWoof woof! I'm Moon! Your personal assistant! 🐾", logo);
    }

    // display goodbye message when exit the bot
    public static String showExitMessage() {
        return "Byeee! Hope to see you again! A-woooooooooooo! 🐾";
    }

    // display prompting message when the chatbot wants to ask
    public static String showAskingMessage() {
        return "How may I help you?";
    }

    // print out the horizontal lines top and bottom of an answer
    public String showLines() {
        return "\t____________________________________________________________";
    }

    public String showExceptionMessage(String s) {
        return "\tMoon: " + s;
    }

    public String showGeneralErrorMessage() {
        return "Sorryyy! I incurred some error while trying to add this task.\nWould you mind trying again? Woof!";
    }

    public String showLoadStorageSuccessfulMessage(TaskList list) {
        return String.format("I have retrieved your previous task list!\n%s\t\t  Woof!", list);
    }

    public String showLoadStorageUnsuccessfulMessage() {
        return "I couldn't retrieve your previous task. No worries! Let's start again! Woof!";
    }

    public String showEmptyInitialStorageMessage() {
        return "Time to start tasking! A-wooooo!";
    }

    public String showAddTaskMessage(Task addedTask) {
        return String.format("Copy that! I've added this task!\n\t\t%s\n", addedTask);
    }

    public String showDeleteTaskMessage(Task deletedTask) {
        return String.format("Copy that! I've deleted this task! Woof!\n\t\t%s\n", deletedTask);
    }

    public String showListMessage(TaskList list) {
        if (list.isEmpty()) {
            return "You haven't added anything to your list yet. Time to start tasking! A-wooooo!";
        } else {
            return String.format("Here are the items in your list!\n%s\t\t  Woof!\n", list);
        }
    }

    public String showAlreadyMarkedMessage(Task alreadyMarkedTask) {
        return String.format("I see you have already marked this task!\n\t\t\t%s\n",
                alreadyMarkedTask);
    }

    public String showAlreadyUnmarkedMessage(Task alreadyUnmarkedTask) {
        return String.format("I see you have already unmarked this task!\n\t\t\t%s\n",
                alreadyUnmarkedTask);
    }

    public String showMarkedSuccessfulMessage(Task markedTask) {
        return String.format("Nicely done! I've pawed this as done! Woof!\n\t\t%s\n",
                markedTask);
    }

    public String showUnmarkedSuccessfulMessage(Task unmarkedTask) {
        return String.format("No worries! I've pawed this as not done! You can do it! Woof!\n\t\t%s\n",
                unmarkedTask);
    }

    public String showTasksMatchedMessage(TaskList matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            return String.format("I can't find any tasks matching your keyword: %s . Wuf!\n", keyword);
        } else {
            return String.format("I found these tasks matching your keyword!\n%s\t\t  Woof!\n", matchedTasks);
        }
    }
}
