package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.FormatChecker;

public class DeleteCommand extends BaseCommand {
    public static final Command COMMAND = Command.DELETE;
    public final int deletedIndex;

    public DeleteCommand(int index) {
        this.deletedIndex = index;
    }

    @Override
    public int execute() throws InvalidIndexException {
        FormatChecker.throwExceptionIfOutOfIndex(deletedIndex, getList());
        Task deletedTask = this.getList().delete(deletedIndex);
        getUi().showDeleteTaskMessage(deletedTask);
        return COMMAND.getStatusCode();
    }
}
