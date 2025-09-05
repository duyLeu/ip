package moon.parser.storage;

import moon.commands.enums.Command;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.FormatChecker;

public class EventStorageParser implements StorageParser<Event> {
    public Event parse(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        FormatChecker.checkCommandFormat(inputList, Command.EVENT);

        MoonDateTime fromTime = DateTimeParser.parse(inputList[1], true);
        MoonDateTime toTime = DateTimeParser.parse(inputList[2], true);
        return new Event(inputList[0], fromTime, toTime);
    }
}
