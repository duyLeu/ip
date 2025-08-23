package exception;

import app.Command;

// Exception thrown when the command keyword does not match up to any keyword in the list.
public class InvalidCommandException extends MoonException {
    private String invalidCommand;

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String invalidCommand, String message) {
        super(message);
        this.invalidCommand = invalidCommand;
    }

    // Method to find the closest matching command
    // primitive version: compare the first letter with all first letters in the keyword lists
    private String findSimilarCommand() {
        return Command.getCommandStream()
                .map(Command::getKeyword)
                .filter(c -> c.startsWith(this.invalidCommand.substring(0,1)))
                .findFirst()
                .map(c -> String.format("Do you mean '%s'?", c))
                .orElse("");
    }

    @Override
    public String getMessage() {
        return String.format("%s: '%s'. %s", super.getMessage(), this.invalidCommand, this.findSimilarCommand());
    }
}
