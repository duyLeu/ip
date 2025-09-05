package moon.models;

import moon.commands.enums.Command;
import moon.parser.exceptions.ParseException;

import moon.parser.util.DateTimeParser;
import moon.parser.util.InputChecker;

// Deadline class represents the 'Deadline' type of tasks, with a description and a deadline
public class Deadline extends Task {
    private final MoonDateTime deadline;
    private static final Command COMMAND = Command.DEADLINE;

    public Deadline(String name, MoonDateTime deadline) {
        super(name);
        this.deadline = deadline;
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
