package exceptions;

import command.Command;

// Exception thrown when list index either not an integer, or out of range of the list.
public class InvalidIndexException extends CommandProcessingException {
    public InvalidIndexException(Command command, String message) {
        super(command, message);
    }
}
