package moon.parser;

import moon.commands.AddTodoCommand;
import moon.commands.enums.Command;
import moon.models.Todo;
import moon.parser.base.Parser;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.ParseException;
import moon.parser.util.InputChecker;

public class AddTodoCommandParser implements Parser<AddTodoCommand> {
    public static final Command COMMAND = Command.EVENT;

    @Override
    // function that parse the string input to initialize a new To-do object
    public AddTodoCommand parse(String input) throws ParseException {
        InputChecker.checkEmptyParameter(input, COMMAND, false);

        String todoName = input.substring(5).trim();
        Todo newTodo = new AddTodoCommand(newTodo);
        return new AddTodoCommand(newTodo);
    }
}
