public class EmptyArgumentException extends CommandProcessingException {
    public EmptyArgumentException(Command command, String message) {
        super(command, message);
    }
}
