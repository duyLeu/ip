public class MoonException extends Exception {
    public MoonException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "\t" + super.getMessage();
    }
}
