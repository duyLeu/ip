package exceptions;

import command.Command;

// Exception thrown when there is a parsing error (i.e. missing dash, typo in 'secondary' keywords,...)
public class ParseException extends CommandProcessingException {
    private String parseError;
    private Command command;

    public ParseException(String message) {
        super(message);
    }

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
