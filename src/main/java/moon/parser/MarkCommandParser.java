package moon.parser;

import moon.commands.DeleteCommand;
import moon.commands.MarkCommand;
import moon.commands.enums.Command;
import moon.parser.base.Parser;
import moon.parser.exceptions.InvalidIndexException;

public class MarkCommandParser implements Parser<MarkCommand> {
    public MarkCommand parse(String input) throws InvalidIndexException {
        // the first line splits the input string then check for the *second* element for the list index
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.MARK,
                    "Wuf! I don't think you put in an integer?");
        }
    }
}
