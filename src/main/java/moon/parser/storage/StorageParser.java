package moon.parser.storage;

import moon.models.Task;
import moon.parser.exceptions.ParseException;

public interface StorageParser<T extends Task> {
    T parse(String[] inputs) throws ParseException;
}
