package moon.parser.exceptions;

import moon.commands.enums.Command;

// Exception thrown when list index either not an integer, or out of range of the list.
public class InvalidIndexException extends ParseException {
    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(Command command, String message) {
        super(command, message);
    }
}
