public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public static Todo fromString(String input) {
        String todoName = input.substring(5);
        return new Todo(todoName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
