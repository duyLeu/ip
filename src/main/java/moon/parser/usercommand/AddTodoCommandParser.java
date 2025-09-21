package moon.parser.usercommand;

import moon.commands.AddTodoCommand;
import moon.commands.enums.Command;
import moon.models.Todo;
import moon.parser.exceptions.ParseException;
import moon.parser.util.ExtractString;
import moon.parser.util.FormatChecker;

/**
 * Parser for the {@link Command#TODO} command.
 * <p>
 * Expected format:
 * <pre>
 *   todo {task description}
 * </pre>
 * Example:
 * <pre>
 *   todo read book
 * </pre>
 */
public class AddTodoCommandParser implements CommandParser<AddTodoCommand> {
    private static final Command COMMAND = Command.TODO;

    /**
     * Parses a user input string into an {@link AddTodoCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddTodoCommand} containing the new {@link Todo}
     * @throws ParseException if the task description is missing or empty
     */
    @Override
    public AddTodoCommand parse(String input) throws ParseException {
        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        FormatChecker.checkEmptyParameter(input, COMMAND, false);

        String todoName = ExtractString.extract(input, COMMAND.getKeyword());
        Todo newTodo = new Todo(todoName);
        return new AddTodoCommand(newTodo);
    }
}
