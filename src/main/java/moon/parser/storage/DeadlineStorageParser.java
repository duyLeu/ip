package moon.parser.storage;

import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;

public class DeadlineStorageParser implements StorageParser<Deadline> {
    public Deadline parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");
        MoonDateTime byTime = DateTimeParser.parse(inputs[3], true);

        Deadline deadline = new Deadline(name, byTime);
        deadline.setDone(done);
        return deadline;
    }
}
