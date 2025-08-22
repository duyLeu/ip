public class CommandProcessingException extends MoonException {
    private Command command;

    public CommandProcessingException(Command command, String message) {
        super(message);
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("%s. You should follow this syntax: '%s'.", super.getMessage(), command.getFormat());
    }
}
