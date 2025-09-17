package moon.commands;

import moon.commands.enums.Command;

/**
 * Command to exit the chatbot.
 */
public class ExitCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.EXIT;

    /**
     * Displays the exit message and returns the status code
     * indicating that the chatbot should terminate.
     *
     * @return confirmation message to be displayed to the user
     */
    @Override
    public String execute() {
        return getUi().showExitMessage();
    }
}
