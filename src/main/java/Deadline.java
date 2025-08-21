public class Deadline extends Task {
    private String deadline;
    private String toTime;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
