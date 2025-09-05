package moon.parser;

import moon.commands.ExitCommand;
import moon.parser.base.Parser;

public class ExitCommandParser implements Parser<ExitCommand> {
    public ExitCommand parse(String s) {
        return new ExitCommand();
    }
}
