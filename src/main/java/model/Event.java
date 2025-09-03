package model;

import exception.EmptyArgumentException;
import exception.ParseException;

import util.InputChecker;

// Event class represents the 'Event' type of tasks, with a description and start-end times
public class Event extends Task {
    private final String fromTime;
    private final String toTime;
    private static final Command command = Command.EVENT;

    public Event(String name, String fromTime, String toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    private String getFromTime() {
        return this.fromTime;
    }

    private String getToTime() {
        return this.toTime;
    }

    // function that parse the string input to initialize a new Event object
    public static Event fromString(String input) throws ParseException, EmptyArgumentException {
        // split the string by the " /" separator into, supposedly, three parts: description, start time, end time
        String[] inputList = input.split(" /");

        InputChecker.checkCommandFormat(inputList, command);
        InputChecker.checkEmptyParameter(inputList[0], command, true);

        String eventName = inputList[0].substring(6);
        String fromKeyword = inputList[1].split(" ")[0];
        String toKeyword = inputList[2].split(" ")[0];

        InputChecker.checkKeyword(fromKeyword, "from", command);
        InputChecker.checkKeyword(toKeyword, "to", command);
        InputChecker.checkEmptyParameter(fromKeyword, command, false);
        InputChecker.checkEmptyParameter(toKeyword, command, false);

        // extract the start-end times from the strings and return an Event object
        String fromTime = inputList[1].substring(5);
        String toTime = inputList[2].substring(3);
        return new Event(eventName, fromTime, toTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.getFromTime(), this.getToTime());
    }
}
