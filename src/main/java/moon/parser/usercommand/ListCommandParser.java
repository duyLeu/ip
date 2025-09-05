package moon.parser.usercommand;

import moon.commands.ListCommand;

public class ListCommandParser implements CommandParser<ListCommand> {
    public ListCommand parse(String s) {
        return new ListCommand();
    }
}
