package moon;

import moon.logic.Moon;

public class Launcher {
    public static void main(String[] args) {
        Moon chatbot = new Moon("src/data/storage.txt");
        chatbot.run();
    }
}
