package moon.models;

// General task class representing any type of task.
public abstract class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

    public abstract String toStorageString();

    @Override
    public String toString() {
        return this.isDone()
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
