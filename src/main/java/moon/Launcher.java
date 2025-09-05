package moon;

import moon.logic.Moon;

// Class to start running the chatbot
public class Launcher {
    public static void main(String[] args) {
        Moon chatbot = new Moon("src/data/storage.txt");
        chatbot.run();
    }
}
