package moon.models;

// General task class representing any type of task.
public abstract class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean b) {
        this.done = b;
    }

    public abstract String toStorageString();

    @Override
    public String toString() {
        return this.isDone()
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
