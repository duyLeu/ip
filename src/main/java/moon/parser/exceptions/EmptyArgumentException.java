package moon.parser.exceptions;

import moon.commands.enums.Command;

// Exception thrown when arguments for commands are empty.
public class EmptyArgumentException extends ParseException {
    public EmptyArgumentException(Command command, String message) {
        super(command, message);
    }
}
