package moon.commands;

import moon.commands.enums.Command;

public class ListCommand extends BaseCommand {
    public static final Command COMMAND = Command.LIST;

    @Override
    public int execute() {
        if (getList().isEmpty()) {
            getUi().showEmptyListMessage();
        } else {
            getUi().showListMessage(getList());
        }
        return COMMAND.getStatusCode();
    }
}
