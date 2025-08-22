public class InvalidCommandException extends MoonException {
    private String invalidCommand;

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String invalidCommand, String message) {
        super(message);
        this.invalidCommand = invalidCommand;
    }

    private String[] findSimilarCommand()

    @Override
    public String getMessage() {
        return String.format("Sorry, I don't recognize this command '%s'", this.invalidCommand);
    }
}
