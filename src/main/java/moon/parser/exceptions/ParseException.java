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

    public ParseException(Command errorCommandType, String message) {
        super(message);
        this.command = errorCommandType;
    }

    public ParseException(String parseError, Command errorCommandType, String message) {
        super(message);
        this.command = errorCommandType;
        this.parseError = parseError;
    }

    public String getParseError() {
        return this.parseError;
    }

    public Command getErrorCommandType() {
        return this.command;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
