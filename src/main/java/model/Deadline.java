package model;

import exception.EmptyArgumentException;
import exception.ParseException;

import util.InputChecker;

// Deadline class represents the 'Deadline' type of tasks, with a description and a deadline
public class Deadline extends Task {
    private final String byTime;
    private static final Command COMMAND = Command.DEADLINE;

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

        InputChecker.checkCommandFormat(inputList, COMMAND);
        InputChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String deadlineName = inputList[0].substring(9);
        String byKeyword = inputList[1].split(" ")[0];

        InputChecker.checkKeyword(byKeyword, "by", COMMAND);
        InputChecker.checkEmptyParameter(byKeyword, COMMAND, false);

        String deadlineTime = inputList[1].substring(3);
        return new Deadline(deadlineName, deadlineTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getByTime());
    }
}
