public class ParseException extends MoonException {
    private String parseError;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String parseError, String message) {
        super(message);
        this.parseError = parseError;
    }

    @Override
    public String getMessage() {
        return String.format("Hmmmm, I don't recognize this keyword '%s'", this.parseError);
    }
}
