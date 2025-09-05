package moon.parser.usercommand;

import moon.commands.DeleteCommand;
import moon.commands.enums.Command;
import moon.parser.exceptions.InvalidIndexException;

public class DeleteCommandParser implements CommandParser<DeleteCommand> {
    public DeleteCommand parse(String input) throws InvalidIndexException {
        // the first line splits the input string then check for the *second* element for the list index
        try {
            int taskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(Command.DELETE,
                    "Wuf! I don't think you put in an integer?");
        }
    }
}
