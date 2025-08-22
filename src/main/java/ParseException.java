public class ParseException extends CommandProcessingException {
    private String parseError;
    private Command command;

    public ParseException(Command command, String message) {
        super(command, message);
    }

    public ParseException(String parseError, Command command, String message) {
        super(command, message);
        this.parseError = parseError;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
