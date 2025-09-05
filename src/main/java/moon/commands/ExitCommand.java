package moon.commands;

import moon.commands.enums.Command;

public class ExitCommand extends BaseCommand {
    public static final Command COMMAND = Command.EXIT;

    @Override
    public int execute() {
        getUi().showExitMessage();
        return COMMAND.getStatusCode();
    }
}
