package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.FormatChecker;

/**
 * Command to delete a {@link Task} task from the task list.
 */
public class DeleteCommand extends BaseCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.DELETE;

    /** Index of the task to be deleted */
    public final int deletedIndex;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index Index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.deletedIndex = index;
    }

    /**
     * Executes the delete command.
     * <p>
     * Removes the specified task from the task list and shows a confirmation message.
     *
     * @return Status code for {@link Command#DELETE}
     * @throws InvalidIndexException If the provided index is out of range
     */
    @Override
    public String execute() throws InvalidIndexException {
        FormatChecker.throwExceptionIfOutOfIndex(deletedIndex, getList());
        Task deletedTask = this.getList().delete(deletedIndex);
        return getUi().showDeleteTaskMessage(deletedTask);
    }
}
