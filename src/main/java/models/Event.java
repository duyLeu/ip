package models;

import command.Command;
import exceptions.EmptyArgumentException;
import exceptions.ParseException;

import util.DateTimeParser;
import util.InputChecker;

// Event class represents the 'Event' type of tasks, with a description and start-end times
public class Event extends Task {
    private final MoonDateTime fromTime;
    private final MoonDateTime toTime;
    private static final Command COMMAND = Command.EVENT;

    private Event(String name, MoonDateTime fromTime, MoonDateTime toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    // function that parse the string input to initialize a new Event object
    public static Event parseFromInput(String input) throws ParseException, EmptyArgumentException {
        // split the string by the " /" separator into, supposedly, three parts: description, start time, end time
        String[] inputList = input.split(" /");

        InputChecker.checkCommandFormat(inputList, COMMAND);
        InputChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String eventName = inputList[0].substring(6).trim();
        String fromKeyword = inputList[1].split(" ")[0];
        String toKeyword = inputList[2].split(" ")[0];

        InputChecker.checkKeyword(fromKeyword, "from", COMMAND);
        InputChecker.checkKeyword(toKeyword, "to", COMMAND);
        InputChecker.checkEmptyParameter(fromKeyword, COMMAND, false);
        InputChecker.checkEmptyParameter(toKeyword, COMMAND, false);

        // extract the start-end times from the strings and return an Event object
        MoonDateTime fromTime = DateTimeParser.parse(inputList[1].substring(5), false);
        MoonDateTime toTime = DateTimeParser.parse(inputList[2].substring(3), false);
        return new Event(eventName, fromTime, toTime);
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
