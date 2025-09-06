package moon.commands;

import moon.commands.enums.Command;
import moon.models.Deadline;

/**
 * Command to add a {@link Deadline} task to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.DEADLINE;

    /** The deadline task to be added. */
    public final Deadline deadline;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param deadline The deadline task to add
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds the deadline to the task list, shows a confirmation message,
     * and returns the associated status code.
     *
     * @return Status code for {@link Command#DEADLINE}
     */
    @Override
    public int execute() {
        addToStorage(deadline);
        getUi().showAddTaskMessage(deadline);
        return COMMAND.getStatusCode();
    }
}
