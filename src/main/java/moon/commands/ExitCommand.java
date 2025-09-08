package moon.commands;

import moon.commands.enums.Command;


public class ExitCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.EXIT;

    /**
     * Displays the exit message and returns the status code
     * indicating that the chatbot should terminate.
     *
     * @return Status code for {@link Command#EXIT}
     */
    @Override
    public int execute() {
        getUi().showExitMessage();
        return COMMAND.getStatusCode();
    }
}
