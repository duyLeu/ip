public class Event extends Task{
    private String fromTime;
    private String toTime;

    public Event(String name, String fromTime, String toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    private String getFromTime() {
        return this.fromTime;
    }

    private String getToTime() {
        return this.toTime;
    }

    public static Event fromString(String input) {

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.getFromTime(), this.getToTime());
    }
}
