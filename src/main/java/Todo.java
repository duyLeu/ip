public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public static Todo fromString(String input) throws EmptyArgumentException {
        if (input.split(" ").length <= 1) {
            throw new EmptyArgumentException(Command.TODO,
                    "Wuff! Your task name cannot be empty!");
        }
        String todoName = input.substring(5);
        return new Todo(todoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
