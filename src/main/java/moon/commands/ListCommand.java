package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;

/**
 * Command to find a {@link Task} in the task list.
 */
public class ListCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.LIST;

    /**
     * Executes the list command to show all the tasks in the task list.
     *
     * @return confirmation message and the task list to be displayed to the user
     */
    @Override
    public String execute() {
        return getUi().showListMessage(getList());
    }
}
