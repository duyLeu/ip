package moon.parser.usercommand;

import moon.commands.UnmarkCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

public class UnmarkCommandParser implements CommandParser<UnmarkCommand> {
    public UnmarkCommand parse(String input) throws InvalidIndexException {
        // the first line splits the input string then check for the *second* element for the list index
        try {
            int taskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.UNMARK,
                    "Wuf! I don't think you put in an integer?");
        }
    }
}
