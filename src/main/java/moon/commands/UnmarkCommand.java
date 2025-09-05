package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.FormatChecker;

public class UnmarkCommand extends BaseCommand {
    public static final Command COMMAND = Command.UNMARK;
    public final int unmarkedIndex;

    public UnmarkCommand(int index) {
        this.unmarkedIndex = index;
    }

    @Override
    public int execute() throws InvalidIndexException {
        FormatChecker.throwExceptionIfOutOfIndex(unmarkedIndex, getList());
        Task taskToUnmark = getList().get(unmarkedIndex);
        if (!taskToUnmark.isDone()) {
            getUi().showAlreadyUnmarkedMessage(taskToUnmark);
        } else {
            taskToUnmark.setDone(false);
            getUi().showUnmarkedSuccessfulMessage(taskToUnmark);
        }
        return COMMAND.getStatusCode();
    }
}
