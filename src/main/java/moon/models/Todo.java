package moon.models;

import moon.commands.enums.Command;
import moon.parser.exceptions.ParseException;
import moon.parser.util.InputChecker;

// To-do class represents the 'Todos' type of tasks, with a description
public class Todo extends Task {
    private static final Command COMMAND = Command.TODO;

    public Todo(String name) {
        super(name);
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
