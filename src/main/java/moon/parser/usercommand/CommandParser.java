package moon.parser.usercommand;

import moon.commands.BaseCommand;
import moon.parser.exceptions.ParseException;

public interface CommandParser<T extends BaseCommand> {
    T parse(String input) throws ParseException;
}
