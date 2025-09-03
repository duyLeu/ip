package exception;

import model.Command;

// Exception thrown for errors in processing the commands
public class CommandProcessingException extends MoonException {
    private final Command command;

    public CommandProcessingException(Command command, String message) {
        super(message);
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("%s. You should follow this syntax: '%s'.", super.getMessage(), command.getFormat());
    }
}
