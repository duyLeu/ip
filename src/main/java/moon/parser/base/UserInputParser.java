package moon.parser.base;

import moon.commands.*;
import moon.commands.enums.Command;
import moon.exceptions.InvalidCommandException;
import moon.models.*;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.exceptions.ParseException;
import moon.parser.*;

public class UserInputParser {
    public static BaseCommand parse(String input) throws ParseException {
        Command command = findMatchingCommand(input);
        // switch-case structure for better readability
        Parser<? extends BaseCommand> p = switch (command) {
            case EXIT -> new ExitCommandParser();
            case LIST -> new ListCommandParser();
            case MARK -> new MarkCommandParser();
            case UNMARK -> new UnmarkCommandParser();
            case TODO -> new AddTodoCommandParser();
            case DEADLINE -> new AddDeadlineCommandParser();
            case EVENT -> new AddEventCommandParser();
            case DELETE -> new DeleteCommandParser();
        };
        return p.parse(input);
    }

    public static Command findMatchingCommand(String input) throws InvalidCommandException {
        String inputKeyword = input.split(" ")[0];

        // Use stream and optional for cleaner code and more functionalities (i.e. filter, findFirst)
        return Command.getCommandStream()
                .parallel()
                .filter(c -> c.getKeyword().equals(inputKeyword))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(inputKeyword));
    }

    public static Task parseFromStorage(String input) throws ParseException {
        return switch (input.substring(0, 1)) {
            case "T" -> Todo.parseFromStorage(input);
            case "D" -> Deadline.parseFromStorage(input);
            case "E" -> Event.parseFromStorage(input);
            default -> throw new ParseException("Unrecognised command in storage.");
        };
    }

    public static void throwExceptionIfOutOfIndex(int index, TaskList list) throws InvalidIndexException {
        if (index < 0 || index >= list.size()) { // check for index range
            throw new InvalidIndexException(Command.DELETE,
                    "Wuf! Your index is out of range!");
        }
    }
}
