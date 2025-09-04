package models;

import command.Command;
import exceptions.EmptyArgumentException;
import exceptions.ParseException;

import util.DateTimeParser;
import util.InputChecker;

// Deadline class represents the 'Deadline' type of tasks, with a description and a deadline
public class Deadline extends Task {
    private final MoonDateTime deadline;
    private static final Command COMMAND = Command.DEADLINE;

    private Deadline(String name, MoonDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    // function that parse the string input to initialize a new Deadline object
    public static Deadline parseFromInput(String input) throws ParseException, EmptyArgumentException {
        // split the string by the " /" separator into, supposedly, two parts: description, and deadline
        String[] inputList = input.split(" /");

        InputChecker.checkCommandFormat(inputList, COMMAND);
        InputChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String deadlineName = inputList[0].substring(9).trim();
        String byKeyword = inputList[1].split(" ")[0];

        InputChecker.checkKeyword(byKeyword, "by", COMMAND);
        InputChecker.checkEmptyParameter(byKeyword, COMMAND, false);

        MoonDateTime deadline = DateTimeParser.parse(inputList[1].substring(3), false);
        return new Deadline(deadlineName, deadline);
    }

    public static Deadline parseFromStorage(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        InputChecker.checkCommandFormat(inputList, COMMAND);

        MoonDateTime deadline = DateTimeParser.parse(inputList[1], true);
        return new Deadline(inputList[0], deadline);
    }

    private String getByTime() {
        return this.deadline.toString();
    }

    @Override
    public String getType() {
        return COMMAND.getKeyword();
    }

    @Override
    public String toStorage() {
        return String.join(" | ",
                "D",
                this.isDone() ? "1" : "0",
                this.getByTime());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getByTime());
    }
}
