package moon.parser.storage;

import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeParser;

public class EventStorageParser implements StorageParser<Event> {
    public Event parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");
        MoonDateTime fromTime = DateTimeParser.parse(inputs[3], true);
        MoonDateTime toTime = DateTimeParser.parse(inputs[4], true);

        Event event = new Event(name, fromTime, toTime);
        event.setDone(done);
        return event;
    }
}
