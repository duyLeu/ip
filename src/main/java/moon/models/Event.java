package moon.models;

// Event class represents the 'Event' type of tasks, with a description and start-end times
public class Event extends Task {
    private final MoonDateTime fromTime;
    private final MoonDateTime toTime;

    public Event(String name, MoonDateTime fromTime, MoonDateTime toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    private String getFromTime() {
        return this.fromTime.toString();
    }

    private String getToTime() {
        return this.toTime.toString();
    }

    @Override
    public String toStorageString() {
        return String.join(" | ",
                "E",
                this.isDone() ? "1" : "0",
                getName(),
                this.getFromTime(),
                this.getToTime());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.getFromTime(), this.getToTime());
    }
}
