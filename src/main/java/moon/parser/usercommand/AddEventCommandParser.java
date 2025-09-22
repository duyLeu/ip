package moon.parser.usercommand;

import moon.commands.AddEventCommand;
import moon.commands.enums.Command;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeChecker;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.ParseChecker;

/**
 * Parser for the {@link Command#EVENT} command.
 * <p>
 * Expected format:
 * <pre>
 *   event {description} /from {start time} /to {end time}
 * </pre>
 * Example:
 * <pre>
 *   event project meeting /from 12/12/2025 1800 /to 12/12/2025 2000
 * </pre>
 */
public class AddEventCommandParser implements CommandParser<AddEventCommand> {
    private static final Command COMMAND = Command.EVENT;
    private static final String PREFIX_FROM = "from";
    private static final String PREFIX_TO = "to";

    /**
     * Parses a user input string into an {@link AddEventCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddEventCommand} containing the new {@link Event}
     * @throws ParseException if the input is malformed (e.g. missing keywords, empty fields, or invalid date/time)
     */
    @Override
    public AddEventCommand parse(String input) throws ParseException {
        String[] inputList = input.split(" /");

        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        // Here it is checking if the event name parameter is empty,
        // and that is there 3 parts separated by the "/"
        ParseChecker.isParameterEmpty(inputList[0], COMMAND, true);
        ParseChecker.isCommandFormatValid(inputList, COMMAND);

        // Extract out the name and the keywords "from" and "to" to check
        String eventName = ExtractString.extract(inputList[0], COMMAND.getKeyword());
        String fromKeyword = inputList[1].split("\\s+")[0];
        String toKeyword = inputList[2].split("\\s+")[0];

        // Check if 1) The keywords are there; 2) The time parameters are there
        ParseChecker.isKeywordValid(fromKeyword, PREFIX_FROM, COMMAND);
        ParseChecker.isKeywordValid(toKeyword, PREFIX_TO, COMMAND);
        ParseChecker.isParameterEmpty(inputList[1], COMMAND, false);
        ParseChecker.isParameterEmpty(inputList[2], COMMAND, false);

        // Extract the from and to date/time.
        // Second parameter of DateTimeParser.parse() is false because it is not extracting from Storage
        MoonDateTime fromTime = DateTimeParser.parse(
                ExtractString.extract(inputList[1], PREFIX_FROM),
                false);
        MoonDateTime toTime = DateTimeParser.parse(
                ExtractString.extract(inputList[2], PREFIX_TO),
                false);
        assert fromTime != null && toTime != null : "DateTimeParser should always return a MoonDateTime or throw";
        DateTimeChecker.isValidEventRange(fromTime, toTime);

        // Create new Event object from the extracted and formatted name and dates
        Event newEvent = new Event(eventName, fromTime, toTime);
        return new AddEventCommand(newEvent);
    }
}
