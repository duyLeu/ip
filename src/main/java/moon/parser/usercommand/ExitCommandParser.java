package moon.parser.usercommand;

import moon.commands.ExitCommand;

public class ExitCommandParser implements CommandParser<ExitCommand> {
    public ExitCommand parse(String s) {
        return new ExitCommand();
    }
}
