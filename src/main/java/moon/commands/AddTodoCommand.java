package moon.commands;

import moon.commands.enums.Command;
import moon.models.Todo;

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
