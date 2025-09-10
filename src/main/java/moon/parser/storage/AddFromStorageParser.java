package moon.parser.storage;

import java.util.Arrays;

import moon.models.Task;
import moon.parser.exceptions.ParseException;

public class AddFromStorageParser {
    public static Task parse(String input) throws ParseException {
        String[] inputList = input.split(" \\| ");
        System.out.println(Arrays.toString(inputList));
        StorageParser<? extends Task> p = switch (inputList[0]) {
        case "T" -> new TodoStorageParser();
        case "D" -> new DeadlineStorageParser();
        case "E" -> new EventStorageParser();
        default -> throw new ParseException("Unrecognised command in storage.");
        };
        return p.parse(inputList);
    }
}
