package model;

import exception.EmptyArgumentException;
import exception.ParseException;
import util.InputChecker;

// To-do class represents the 'Todos' type of tasks, with a description
public class Todo extends Task {
    private static final Command COMMAND = Command.TODO;

    public Todo(String name) {
        super(name);
    }

    // function that parse the string input to initialize a new To-do object
    public static Todo parseFromInput(String input) throws ParseException, EmptyArgumentException {
        InputChecker.checkEmptyParameter(input, COMMAND, false);

        String todoName = input.substring(5).trim();
        return new Todo(todoName);
    }

    public static Todo parseFromStorage(String input) throws ParseException {
        String[] inputList = input.split(" | ");
        InputChecker.checkCommandFormat(inputList, COMMAND);

        return new Todo(inputList[0]);
    }

    @Override
    public String getType() {
        return COMMAND.getKeyword();
    }

    @Override
    public String toStorage() {
        return String.join(" | ",
                "T",
                this.isDone() ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
