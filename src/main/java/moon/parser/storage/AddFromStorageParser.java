package moon.parser.storage;

import moon.commands.BaseCommand;
import moon.models.Task;
import moon.parser.exceptions.ParseException;

public class AddFromStorageParser {
    public static Task parse(String input) throws ParseException {
        StorageParser<? extends Task> p = switch (input.substring(0, 1)) {
            case "T" -> new TodoStorageParser();
            case "D" -> new DeadlineStorageParser();
            case "E" -> new EventStorageParser();
            default -> throw new ParseException("Unrecognised command in storage.");
        };
        return p.parse(input);
    }
}
