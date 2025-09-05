package moon.parser;

import moon.commands.ListCommand;
import moon.parser.base.Parser;

public class ListCommandParser implements Parser<ListCommand> {
    public ListCommand parse(String s) {
        return new ListCommand();
    }
}
