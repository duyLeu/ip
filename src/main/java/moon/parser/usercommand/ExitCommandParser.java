package moon.parser.usercommand;

import moon.commands.ExitCommand;

public class ExitCommandParser implements CommandParser<ExitCommand> {
    public ExitCommand parse(String input) {
        return new ExitCommand();
    }
}
