package exception;

// General exception class for all errors thrown by the chatbot
public class MoonException extends Exception {
    public MoonException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "\tMoon: " + super.getMessage();
    }
}
