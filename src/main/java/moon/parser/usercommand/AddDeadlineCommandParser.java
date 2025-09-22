package moon.parser.usercommand;

import moon.commands.AddDeadlineCommand;
import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeChecker;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.ParseChecker;

/**
 * Parser for the {@link Command#DEADLINE} command.
 * <p>
 * Expected format:
 * <pre>
 *   deadline {description} /by {deadline time}
 * </pre>
 * Example:
 * <pre>
 *   deadline return book /by 25/12/2025 1800
 * </pre>
 */
public class AddDeadlineCommandParser implements CommandParser<AddDeadlineCommand> {
    private static final Command COMMAND = Command.DEADLINE;
    private static final String PREFIX_BY = "by";

    /**
     * Parses a user input string into an {@link AddDeadlineCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddDeadlineCommand} containing the new {@link Deadline}
     * @throws ParseException if the input is malformed
     *                        (e.g. missing description, missing "/by" keyword, or invalid date/time format)
     */
    @Override
    public AddDeadlineCommand parse(String input) throws ParseException {
        String[] inputList = input.split(" /");

        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        // Here it is checking if the event name parameter is empty,
        // and that is there 3 parts separated by the "/"
        ParseChecker.isParameterEmpty(inputList[0], COMMAND, true);
        ParseChecker.isCommandFormatValid(inputList, COMMAND);

        // Extract out the name and the keyword "by" to check
        String deadlineName = ExtractString.extract(inputList[0], COMMAND.getKeyword());
        String byKeyword = inputList[1].split("\\s+")[0];

        // Check if 1) The keyword is there; 2) The time parameter is there
        ParseChecker.isKeywordValid(byKeyword, PREFIX_BY, COMMAND);
        ParseChecker.isParameterEmpty(inputList[1], COMMAND, false);

        // Extract the by date/time.
        // Second parameter of DateTimeParser.parse() is false because it is not extracting from Storage
        MoonDateTime deadlineTime = DateTimeParser.parse(
                ExtractString.extract(inputList[1], PREFIX_BY),
                false);
        assert deadlineTime != null : "DateTimeParser should always return a MoonDateTime or throw";
        DateTimeChecker.isNotBeforeToday(deadlineTime);

        // Create new Deadline object from the extracted and formatted name and dates
        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        return new AddDeadlineCommand(newDeadline);
    }
}
