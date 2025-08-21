import java.util.Scanner;

// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private final Task[] taskList;  // list of task
    private int listIndex;  // list index

    // Constructor for class Moon
    public Moon() {
        this.taskList = new Task[100];
        this.listIndex = 0;
    }

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
        System.out.println("\tMoon: Byeee! Hope to see you again! A-woooooooooooo! 🐾\n");
    }

    // display prompting message when the chatbot wants to ask
    public void getAskingMessage() {
        System.out.println("\tMoon: How may I help you?");
    }

    // display the task list
    public void getListDisplay() {
        for (int i = 0; i < listIndex; i++) {
            System.out.printf("\t\t  %d. %s\n", i + 1, taskList[i]);
        }
    }

    // print out the horizontal lines top and bottom of an answer
    public void getHorizontalLines() {
        System.out.println("\t____________________________________________________________");
    }

    // handle the string input
    public int handleInput(String input) {
        try {
            Command command = Command.fromString(input);
            switch (command) {
                case EXIT:
                    this.getExitMessage();
                    break;
                case LIST:
                    // Display all the items in the list
                    System.out.println("\tMoon: Here are the items in your list!");
                    this.getListDisplay();
                    System.out.println("\t\t  Woof!\n");
                    break;
                case MARK:
                    // extract the index and set done accordingly
                    int markIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList[markIndex].setDone();
                    System.out.printf("\tMoon: Nicely done! I've pawed this as done! Woof!\n\t\t\t%s\n",
                            taskList[markIndex]);
                    break;
                case UNMARK:
                    // extract the index and set undone accordingly
                    int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    taskList[unmarkIndex].setNotDone();
                    System.out.printf("\tMoon: No worries! I've pawed this as not done! You can do it! Woof!\n\t\t\t%s\n",
                            taskList[unmarkIndex]);
                    break;
                case TODO:
                    String taskName = input.substring(5);
                    Task newTask = new Task(taskName);
                    taskList[listIndex] = newTask;
                    listIndex += 1;
                    System.out.printf("\tMoon: '%s' is added! Woof!\n", taskName);
                case DEADLINE:
                    break;
                case EVENT:
                    break;
            }
            return command.getStatusCode();
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unknown command.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number.");
        }
    }

    // function start the chatbot
    public void run() {
        this.getGreetingMessage();

        // create a Scanner object to scan the next line
        Scanner myScanner = new Scanner(System.in);

        // the loop keep prompting the user until it receive the string "bye"
        int statusCode = 1; // status code 1 is default for running
        while (statusCode != 0) {
            // start by prompting question
            this.getAskingMessage();
            // scan user input
            String userInput = myScanner.nextLine();
            // process user input, while returning
            try {
                this.getHorizontalLines();
                statusCode = this.handleInput(userInput);
                this.getHorizontalLines();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command. Reason: " + e.getMessage());
                statusCode = -1;
            }
        }
    }
}