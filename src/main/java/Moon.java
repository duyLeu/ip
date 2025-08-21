import java.util.Objects;
import java.util.Scanner;

public class Moon {

    // Constructor for class Moon
    public Moon() {
    }

    // function start the chatbot
    public void run() {
        this.getGreetingMessage();

        // create a Scanner object to scan the next line
        Scanner myScanner = new Scanner(System.in);
        this.getAskingMessage();
        String userInput = myScanner.nextLine();

        // the loop keep prompting the user until it receive the string "bye"
        while (!Objects.equals(userInput, "bye")) {
            System.out.printf("\t_________________\n\t%s\n\t_________________%n", userInput);
            this.getAskingMessage();
            userInput = myScanner.nextLine();
        }
        this.getExitMessage();
    }

    // display greeting message
    public void getGreetingMessage() {
        String logo =
                " __  __   ____    ____    _   _ \n"
                        + "|  \\/  | / __ \\  / __ \\  | \\ | |     __      _\n"
                        + "| |\\/| || |  | || | () | |  \\| |   o'')}____//\n"
                        + "| |  | || |__| || @ . o| | |\\  |    `_/      )\n"
                        + "|_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/\n";
        String output = String.format("%sWoof woof! I'm Moooon! Your personal assistant! 🐾\n", logo);
        System.out.println(output);
    }

    // display prompting message when the chatbot wants to ask
    public void getAskingMessage() {
        System.out.println("How can I help you?");
    }

    // display goodbye message when exit the bot
    public void getExitMessage() {
        System.out.println("Byeee! Hope to see you again! Woof! 🐾\n");
    }
}