public class Moon {

    public Moon() {
    }

    public void getGreetingMessage() {
        String logo =
                " __  __   ____    ____    _   _ \n"
                        + "|  \\/  | / __ \\  / __ \\  | \\ | |     __      _\n"
                        + "| |\\/| || |  | || | () | |  \\| |   o'')}____//\n"
                        + "| |  | || |__| || @ . o| | |\\  |    `_/      )\n"
                        + "|_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/\n";
        String output = String.format("%sWoof! I'm Moon! Ready to chat! 🐾\n", logo);
        System.out.println(output);
    }

    public void getExitMessage() {
        System.out.println("Bye! Hope to see you again! Woof! 🐾\n");
    }

    public static void main(String[] args) {
        Moon chatbot = new Moon();
        chatbot.getGreetingMessage();
        chatbot.getExitMessage();
    }
}