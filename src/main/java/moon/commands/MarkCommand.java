package moon.commands;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.util.FormatChecker;

public class MarkCommand extends BaseCommand {
    public static final Command COMMAND = Command.MARK;
    public final int markedIndex;

    public MarkCommand(int index) {
        this.markedIndex = index;
    }

    @Override
    public int execute() throws InvalidIndexException {
        FormatChecker.throwExceptionIfOutOfIndex(markedIndex, getList());
        Task taskToMark = getList().get(markedIndex);
        if (taskToMark.isDone()) {
            getUi().showAlreadyMarkedMessage(taskToMark);
        } else {
            taskToMark.setDone();
            getUi().showMarkedSuccessfulMessage(taskToMark);
        }
        return COMMAND.getStatusCode();
    }
}
