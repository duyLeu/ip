package moon.parser.usercommand;

import moon.commands.AddDeadlineCommand;
import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;
import moon.parser.util.FormatChecker;

public class AddDeadlineCommandParser implements CommandParser<AddDeadlineCommand> {
    public static final Command COMMAND = Command.DEADLINE;

    @Override
    // function that parse the string input to initialize a new Deadline object
    public AddDeadlineCommand parse(String input) throws ParseException {
        // split the string by the " /" separator into, supposedly, two parts: description, and deadline
        String[] inputList = input.split(" /");

        FormatChecker.checkCommandFormat(inputList, COMMAND);
        FormatChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String deadlineName = inputList[0].substring(9).trim();
        String byKeyword = inputList[1].split(" ")[0];

        FormatChecker.checkKeyword(byKeyword, "by", COMMAND);
        FormatChecker.checkEmptyParameter(byKeyword, COMMAND, false);

        MoonDateTime deadlineTime = DateTimeParser.parse(inputList[1].substring(3), false);
        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        return new AddDeadlineCommand(newDeadline);
    }
}
