public class InvalidCommandException extends MoonException {
    private String invalidCommand;

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String invalidCommand, String message) {
        super(message);
        this.invalidCommand = invalidCommand;
    }

    private String findSimilarCommand() {
        return Command.getCommandStream()
                .map(Command::getKeyword)
                .filter(c -> c.startsWith(this.invalidCommand.substring(0,2)))
                .findFirst()
                .map(c -> String.format("Do you mean '%s'?", c))
                .orElse("");
    }

    @Override
    public String getMessage() {
        return String.format("%s: '%s'. %s", super.getMessage(), this.invalidCommand, this.findSimilarCommand());
    }
}
