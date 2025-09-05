package util;

import command.*;
import exceptions.InvalidCommandException;
import exceptions.MoonException;
import exceptions.ParseException;

import models.*;

public class Parser {
    public static BaseCommand parseFromInput(String s) throws InvalidCommandException {
        Command command = findMatchingCommand(s);
        // switch-case structure for better readability
        return switch (command) {
            case EXIT -> new ExitCommand(s);
            case LIST -> new ListCommand(s);
            case MARK -> new MarkCommand(s);
            case UNMARK -> new UnmarkCommand(s);
            case TODO -> new AddTodoCommand(s);
            case DEADLINE -> new AddDeadlineCommand(s);
            case EVENT -> new AddEventCommand(s);
            case DELETE -> new DeleteCommand(s);
        };
    }

    public static Command findMatchingCommand(String input) throws InvalidCommandException {
        String inputKeyword = input.split(" ")[0];

        // Use stream and optional for cleaner code and more functionalities (i.e. filter, findFirst)
        return Command.getCommandStream()
                .parallel()
                .filter(c -> c.getKeyword().equals(inputKeyword))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(inputKeyword,
                        "Wuf? Are you sure you have the correct command?"));
    }

    public static Task parseFromStorage(String input) throws ParseException, InvalidCommandException {
        return switch (input.substring(0, 1)) {
            case "T" -> Todo.parseFromStorage(input);
            case "D" -> Deadline.parseFromStorage(input);
            case "E" -> Event.parseFromStorage(input);
            default -> throw new InvalidCommandException("Command not found");
        };
    }
}
