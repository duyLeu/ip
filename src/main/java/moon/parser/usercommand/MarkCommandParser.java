package moon.parser.usercommand;

import moon.commands.MarkCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

public class MarkCommandParser implements CommandParser<MarkCommand> {
    public MarkCommand parse(String input) throws InvalidIndexException {
        // the first line splits the input string then check for the *second* element for the list index
        try {
            int taskIndex = Integer.parseInt(input.split("\\s+")[1].trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.MARK,
                    "Wuf! I don't think you put in an integer?");
        }
    }
}
