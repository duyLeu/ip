package moon.exceptions;

// General exception class for all errors thrown by the chatbot
public class MoonException extends Exception {
    public MoonException(String message) {
        super(message);
    }
}
