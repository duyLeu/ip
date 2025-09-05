package moon.parser.exceptions;

import moon.commands.enums.Command;
import moon.exceptions.MoonException;

// Exception thrown when there is a parsing error (i.e. missing dash, typo in 'secondary' keywords,...)
public class ParseException extends MoonException {
    private String parseError;
    private Command command;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Command command, String message) {
        super(message);
        this.command = command;
    }

    public ParseException(String parseError, Command command, String message) {
        super(message);
        this.command = command;
        this.parseError = parseError;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
