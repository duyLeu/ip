package moon.models;

import moon.commands.enums.Command;
import moon.parser.exceptions.ParseException;

import moon.parser.util.DateTimeParser;
import moon.parser.util.InputChecker;

// Event class represents the 'Event' type of tasks, with a description and start-end times
public class Event extends Task {
    private final MoonDateTime fromTime;
    private final MoonDateTime toTime;
    private static final Command COMMAND = Command.EVENT;

    public Event(String name, MoonDateTime fromTime, MoonDateTime toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public static Event parseFromStorage(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        InputChecker.checkCommandFormat(inputList, COMMAND);

        MoonDateTime fromTime = DateTimeParser.parse(inputList[1], true);
        MoonDateTime toTime = DateTimeParser.parse(inputList[2], true);
        return new Event(inputList[0], fromTime, toTime);
    }

    private String getFromTime() {
        return this.fromTime.toString();
    }

    private String getToTime() {
        return this.toTime.toString();
    }

    @Override
    public String getType() {
        return COMMAND.getKeyword();
    }

    @Override
    public String toStorage() {
        return String.join(" | ",
                "E",
                this.isDone() ? "1" : "0",
                this.getFromTime(),
                this.getToTime());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.getFromTime(), this.getToTime());
    }
}
