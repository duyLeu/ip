package moon.parser.base;

import moon.commands.BaseCommand;
import moon.parser.exceptions.ParseException;

public interface Parser<T extends BaseCommand> {
    T parse(String input) throws ParseException;
}
