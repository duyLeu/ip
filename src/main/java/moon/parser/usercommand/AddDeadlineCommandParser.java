package moon.parser.usercommand;

import moon.commands.AddDeadlineCommand;
import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.FormatChecker;

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
        FormatChecker.checkEmptyParameter(inputList[0], COMMAND, true);
        FormatChecker.checkCommandFormat(inputList, COMMAND);

        String deadlineName = ExtractString.extract(inputList[0], COMMAND.getKeyword());
        String byKeyword = inputList[1].split("\\s+")[0];

        FormatChecker.checkKeyword(byKeyword, PREFIX_BY, COMMAND);
        FormatChecker.checkEmptyParameter(inputList[1], COMMAND, false);

        MoonDateTime deadlineTime = DateTimeParser.parse(
                ExtractString.extract(inputList[1], PREFIX_BY),
                false);

        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        return new AddDeadlineCommand(newDeadline);
    }
}
