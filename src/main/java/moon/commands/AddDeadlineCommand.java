package moon.commands;

import moon.commands.enums.Command;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.ParseException;
import moon.models.Deadline;

public class AddDeadlineCommand extends AddCommand {
    public static final Command COMMAND = Command.DEADLINE;
    public final Deadline deadline;

    public AddDeadlineCommand(Deadline c) {
        this.deadline = c;
    }

    @Override
    public int execute() {
        addToStorage(deadline);
        getUi().showAddTaskMessage(deadline);
        return COMMAND.getStatusCode();
    }
}
