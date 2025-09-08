package moon.parser.usercommand;

import moon.commands.FindTaskCommand;

public class FindTaskCommandParser implements CommandParser<FindTaskCommand> {
    public FindTaskCommand parse(String input) {
        String keyword = input.split("\\s+", 2)[1];
        return new FindTaskCommand(keyword);
    }
}
