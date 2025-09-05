package moon.parser.storage;

import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.FormatChecker;

public class DeadlineStorageParser implements StorageParser<Deadline> {
    public Deadline parse(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        FormatChecker.checkCommandFormat(inputList, Command.DEADLINE);

        MoonDateTime deadline = DateTimeParser.parse(inputList[1], true);
        return new Deadline(inputList[0], deadline);
    }
}
