package app;

import exception.EmptyArgumentException;
import exception.ParseException;

// Deadline class represents the 'Deadline' type of tasks, with a description and a deadline
public class Deadline extends Task {
    private String byTime;

    public Deadline(String name, String deadline) {
        super(name);
        this.byTime = deadline;
    }

    private String getByTime() {
        return this.byTime;
    }

    // function that parse the string input to initialize a new Deadline object
    public static Deadline fromString(String input) throws ParseException, EmptyArgumentException {
        // split the string by the " /" separator into, supposedly, two parts: description, and deadline
        String[] inputList = input.split(" /");
        if (inputList.length != 2) {
            throw new ParseException(input, Command.DEADLINE,
                    "Wuf! are you missing a dash '/' somewhere?");
        }

        String deadlineName = inputList[0].substring(9);

        String byKeyword = inputList[1].split(" ")[0];
        // check keyword 'by' exist, and empty arguments
        if (!byKeyword.equals("by")) {
            throw new ParseException(byKeyword, Command.DEADLINE,
                    String.format("Wuf! I think you make a mistake here: '%s'", byKeyword));

        } else if (inputList[0].split(" ").length <= 1) {
            throw new EmptyArgumentException(Command.DEADLINE,
                    "Wuf! Your task name cannot be empty!");

        } else if (inputList[1].split(" ").length <= 1) {
            throw new EmptyArgumentException(Command.DEADLINE,
                    "Wuf! Your deadline time cannot be empty!");
        }
        // extract the start-end times from the strings and return an Event object
        String deadlineTime = inputList[1].substring(3);
        return new Deadline(deadlineName, deadlineTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getByTime());
    }
}
