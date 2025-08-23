package app;

import exception.EmptyArgumentException;

// To-do class represents the 'Todos' type of tasks, with a description
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    // function that parse the string input to initialize a new To-do object
    public static Todo fromString(String input) throws EmptyArgumentException {
        if (input.split(" ").length <= 1) { // check empty argument
            throw new EmptyArgumentException(Command.TODO,
                    "Wuf! Your task name cannot be empty!");
        }
        String todoName = input.substring(5);
        return new Todo(todoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
