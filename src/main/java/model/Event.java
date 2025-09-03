package model;

import exception.EmptyArgumentException;
import exception.ParseException;

// Event class represents the 'Event' type of tasks, with a description and start-end times
public class Event extends Task {
    private String fromTime;
    private String toTime;

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
        if (inputList.length < 3) {
            throw new ParseException(input, Command.EVENT,
                    "Wuf! Are you missing a dash '/' or a command somewhere?");
        } else if (inputList.length > 3) {
            throw new ParseException(input, Command.EVENT,
                    "Wuf! Are you sure you have the correct command?");
        }

        if (inputList[0].split(" ").length <= 1) {
            // check empty event name argument
            throw new EmptyArgumentException(Command.EVENT,
                    "Wuf! Your task name cannot be empty!");

        }
        String eventName = inputList[0].substring(6);

        String fromKeyword = inputList[1].split(" ")[0];
        String toKeyword = inputList[2].split(" ")[0];

        // check keywords 'from' and 'to' exist, and empty start-end time argument
        if (!fromKeyword.equals("from")) {
            throw new ParseException(fromKeyword, Command.EVENT,
                    String.format("Wuf! I think you make a mistake here: '%s'", fromKeyword));

        } else if (!toKeyword.equals("to")) {
            throw new ParseException(toKeyword, Command.EVENT,
                    String.format("Wuf! I think you make a mistake here: '%s'", toKeyword));

        } else if (inputList[1].split(" ").length <= 1 || inputList[2].split(" ").length <= 1) {
            throw new EmptyArgumentException(Command.EVENT,
                    "Wuf! Both your start and end time cannot be empty!");
        }
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
