package moon.parser.usercommand;

import moon.commands.ListCommand;

public class ListCommandParser implements CommandParser<ListCommand> {
    public ListCommand parse(String input) {
        return new ListCommand();
    }
}
