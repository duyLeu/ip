package moon.parser.usercommand;

import moon.commands.AddTodoCommand;
import moon.commands.enums.Command;
import moon.models.Todo;
import moon.parser.exceptions.ParseException;
import moon.parser.util.ExtractString;
import moon.parser.util.FormatChecker;

public class AddTodoCommandParser implements CommandParser<AddTodoCommand> {
    public static final Command COMMAND = Command.EVENT;

    @Override
    // function that parse the string input to initialize a new To-do object
    public AddTodoCommand parse(String input) throws ParseException {
        FormatChecker.checkEmptyParameter(input, COMMAND, false);

        String todoName = ExtractString.extract(input, COMMAND.getKeyword());
        Todo newTodo = new Todo(todoName);
        return new AddTodoCommand(newTodo);
    }
}
