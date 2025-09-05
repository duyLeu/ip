package moon.parser;

import moon.commands.AddEventCommand;
import moon.commands.enums.Command;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.base.Parser;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.InputChecker;

public class AddEventCommandParser implements Parser<AddEventCommand> {
    public static final Command COMMAND = Command.EVENT;

    @Override
    public AddEventCommand parse(String input) throws ParseException {
        // split the string by the " /" separator into, supposedly, three parts: description, start time, end time
        String[] inputList = input.split(" /");

        InputChecker.checkCommandFormat(inputList, COMMAND);
        InputChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String eventName = inputList[0].substring(6).trim();
        String fromKeyword = inputList[1].split(" ")[0];
        String toKeyword = inputList[2].split(" ")[0];

        InputChecker.checkKeyword(fromKeyword, "from", COMMAND);
        InputChecker.checkKeyword(toKeyword, "to", COMMAND);
        InputChecker.checkEmptyParameter(fromKeyword, COMMAND, false);
        InputChecker.checkEmptyParameter(toKeyword, COMMAND, false);

        // extract the start-end times from the strings and return an Event object
        MoonDateTime fromTime = DateTimeParser.parse(inputList[1].substring(5), false);
        MoonDateTime toTime = DateTimeParser.parse(inputList[2].substring(3), false);
        Event newEvent = new Event(eventName, fromTime, toTime);
        return new AddEventCommand(newEvent);
    }
}
