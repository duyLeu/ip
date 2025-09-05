package moon.commands;

import moon.commands.enums.Command;
import moon.parser.base.UserInputParser;
import moon.parser.exceptions.InvalidIndexException;
import moon.models.Task;

public class DeleteCommand extends BaseCommand {
    public static final Command COMMAND = Command.DELETE;
    public final int deletedIndex;

    public DeleteCommand(int index) {
        this.deletedIndex = index;
    }

    @Override
    public int execute() throws InvalidIndexException {
        UserInputParser.throwExceptionIfOutOfIndex(deletedIndex, getList());
        Task deletedTask = this.getList().delete(deletedIndex);
        getUi().showDeleteTaskMessage(deletedTask);
        return COMMAND.getStatusCode();
    }
}
