package moon.logic;

// Class to start running the chatbot
public class Main {
    public static void main(String[] args) {
        Moon chatbot = new Moon("src/data/storage.txt");
        chatbot.run();
    }
}
