public class InvalidIndexException extends CommandProcessingException {
    public InvalidIndexException(Command command, String message) {
        super(command, message);
    }
}
