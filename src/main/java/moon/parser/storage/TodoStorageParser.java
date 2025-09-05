package moon.parser.storage;

import moon.commands.enums.Command;
import moon.models.Todo;
import moon.parser.exceptions.ParseException;
import moon.parser.util.FormatChecker;

public class TodoStorageParser implements StorageParser<Todo> {
    public Todo parse(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        FormatChecker.checkCommandFormat(inputList, Command.TODO);

        return new Todo(inputList[0]);
    }
}
