package moon.parser.usercommand;

import moon.commands.AddDeadlineCommand;
import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.FormatChecker;

public class AddDeadlineCommandParser implements CommandParser<AddDeadlineCommand> {
    private static final Command COMMAND = Command.DEADLINE;
    private static final String PREFIX_BY = "by";
;
    @Override
    // function that parse the string input to initialize a new Deadline object
    public AddDeadlineCommand parse(String input) throws ParseException {
        // split the string by the " /" separator into, supposedly, two parts: description, and deadline
        String[] inputList = input.split(" /");

        FormatChecker.checkCommandFormat(inputList, COMMAND);
        FormatChecker.checkEmptyParameter(inputList[0], COMMAND, true);

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
