package moon.models;

// Deadline class represents the 'Deadline' type of tasks, with a description and a deadline
public class Deadline extends Task {
    private final MoonDateTime deadline;

    public Deadline(String name, MoonDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    private String getByTime() {
        return this.deadline.toString();
    }

    @Override
    public String toStorageString() {
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
