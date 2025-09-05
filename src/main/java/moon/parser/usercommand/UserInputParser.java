package moon.parser.usercommand;

import moon.commands.BaseCommand;
import moon.commands.enums.Command;
import moon.commands.exceptions.InvalidCommandException;
import moon.parser.exceptions.ParseException;

public class UserInputParser {
    public static BaseCommand parse(String input) throws ParseException {
        Command command = findMatchingCommand(input);
        // switch-case structure for better readability
        CommandParser<? extends BaseCommand> p = switch (command) {
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
}
