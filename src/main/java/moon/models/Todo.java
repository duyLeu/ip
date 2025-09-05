package moon.models;

// To-do class represents the 'Todos' type of tasks, with a description
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toStorageString() {
        return String.join(" | ",
                "T",
                this.isDone() ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
