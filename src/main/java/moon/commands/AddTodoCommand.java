package moon.commands;

import moon.commands.enums.Command;
import moon.models.Todo;

/**
 * Command to add a {@link Todo} task to the task list.
 */
public class AddTodoCommand extends AddCommand {
    /** Associated command keyword and status code. */
    public static final Command COMMAND = Command.TODO;

    /** The todo task to be added. */
    public final Todo todo;

    /**
     * Creates a new AddTodoCommand.
     *
     * @param todo The todo task to add
     */
    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Adds the todo to the task list, shows a confirmation message,
     * and returns the associated status code.
     *
     * @return Status code for {@link Command#TODO}
     */
    @Override
    public String execute() {
        addToStorage(todo);
        return getUi().showAddTaskMessage(todo);
    }
}
