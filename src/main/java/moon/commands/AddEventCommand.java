package moon.commands;

import moon.commands.enums.Command;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.ParseException;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.util.DateTimeParser;
import moon.parser.util.InputChecker;

public class AddEventCommand extends AddCommand {
    public static final Command COMMAND = Command.EVENT;
    public final Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public int execute() {
        addToStorage(event);
        getUi().showAddTaskMessage(event);
        return COMMAND.getStatusCode();
    }
}
