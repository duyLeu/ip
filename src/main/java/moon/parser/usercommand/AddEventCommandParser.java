package moon.parser.usercommand;

import moon.commands.AddEventCommand;
import moon.commands.enums.Command;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.FormatChecker;

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

        FormatChecker.checkEmptyParameter(inputList[0], COMMAND, true);
        FormatChecker.checkCommandFormat(inputList, COMMAND);

        String eventName = ExtractString.extract(inputList[0], COMMAND.getKeyword());
        String fromKeyword = inputList[1].split("\\s+")[0];
        String toKeyword = inputList[2].split("\\s+")[0];

        FormatChecker.checkKeyword(fromKeyword, PREFIX_FROM, COMMAND);
        FormatChecker.checkKeyword(toKeyword, PREFIX_TO, COMMAND);
        FormatChecker.checkEmptyParameter(inputList[1], COMMAND, false);
        FormatChecker.checkEmptyParameter(inputList[2], COMMAND, false);

        MoonDateTime fromTime = DateTimeParser.parse(
                ExtractString.extract(inputList[1], PREFIX_FROM),
                false);
        MoonDateTime toTime = DateTimeParser.parse(
                ExtractString.extract(inputList[2], PREFIX_TO),
                false);

        Event newEvent = new Event(eventName, fromTime, toTime);
        return new AddEventCommand(newEvent);
    }
}
