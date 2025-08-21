import java.util.Objects;
import java.util.Scanner;

// OT: The chatbot is named Moon after my pet puppy, that's why there's a puppy in the logo.
public class Moon {

    private Task[] taskList;  // list of task
    private int listIndex;  // list index

    // Constructor for class Moon
    public Moon() {
        this.taskList = new Task[100];
        this.listIndex = 0;
    }

    // display greeting message
    public void getGreetingMessage() {
        String logo =
                " __  __   ____    ____    _   _ \n"
                + "|  \\/  | / __ \\  / __ \\  | \\ | |     __      _\n"
                + "| |\\/| || |  | || | () | |  \\| |   o'')}____//\n"
                + "| |  | || |__| || @ . o| | |\\  |    `_/      )\n"
                + "|_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/\n";
        System.out.printf("%s\tMoon: Woof woof! I'm Moon! Your personal assistant! 🐾" +
                        "\n\t\t  How may I help you today!\n", logo);
    }

    // display goodbye message when exit the bot
    public void getExitMessage() {
        System.out.println("\tMoon: Byeee! Hope to see you again! Woof! 🐾\n");
    }

    // display prompting message when the chatbot wants to ask
    public void getAskingMessage() {
        System.out.println("\t\t  Do you need anything else?");
    }

    // display the task list
    public void getListDisplay() {
        for (int i = 0; i < listIndex; i++) {
            System.out.printf("\t\t  %d. %s\n", i + 1, taskList[i]);
        }
    }

    // print out the horizontal lines top and bottom of an answer
    public void getHorizontalLines() {
        System.out.println("\t____________________");
    }

    // function start the chatbot
    public void run() {
        this.getGreetingMessage();

        // create a Scanner object to scan the next line
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();

        // the loop keep prompting the user until it receive the string "bye"
        while (!Objects.equals(userInput, "bye")) {
            this.getHorizontalLines();

            if (Objects.equals(userInput, "list")) {
                // Display all the items in the list
                System.out.println("\tMoon: Here are the items in your list!");
                this.getListDisplay();
                System.out.println("\t\t  Woof!\n");
            } else {
                // Add item into the list, returning a successful message
                Task newTask = new Task(userInput);
                taskList[listIndex] = newTask;
                listIndex += 1;
                System.out.printf("\tMoon: '%s' is added! Woof!\n", userInput);
            }
            this.getAskingMessage();
            this.getHorizontalLines();
            // prepare for the next inquiry
            userInput = myScanner.nextLine();
        }

        // display exit message
        this.getExitMessage();
    }
}