package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.base.UserInputParser;
import moon.parser.exceptions.InvalidIndexException;

public class UnmarkCommand extends BaseCommand {
    public static final Command COMMAND = Command.UNMARK;
    public final int unmarkedIndex;

    public UnmarkCommand(int index) {
        this.unmarkedIndex = index;
    }

    @Override
    public int execute() throws InvalidIndexException {
        UserInputParser.throwExceptionIfOutOfIndex(unmarkedIndex, getList());
        Task taskToUnmark = getList().get(unmarkedIndex);
        if (!taskToUnmark.isDone()) {
            getUi().showAlreadyUnmarkedMessage(taskToUnmark);
        } else {
            taskToUnmark.setNotDone();
            getUi().showUnmarkedSuccessfulMessage(taskToUnmark);
        }
        return COMMAND.getStatusCode();
    }
}
