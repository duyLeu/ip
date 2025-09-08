package moon.commands;

import moon.commands.enums.Command;

public class ListCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.LIST;

    /**
     * Executes the list command to show all the tasks in the task list.
     * <ul>
     *   <li>If the list is empty, shows an empty list message.</li>
     *   <li>Otherwise, shows the formatted list of tasks.</li>
     * </ul>
     *
     * @return Status code for {@link Command#LIST}
     */
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
