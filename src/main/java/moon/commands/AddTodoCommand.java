package moon.commands;

import moon.commands.enums.Command;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.ParseException;
import moon.models.Todo;
import moon.parser.util.InputChecker;

public class AddTodoCommand extends AddCommand {
    public static final Command COMMAND = Command.TODO;
    public final Todo todo;

    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public int execute() {
        addToStorage(todo);
        getUi().showAddTaskMessage(todo);
        return COMMAND.getStatusCode();
    }
}
