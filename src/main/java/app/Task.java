package app;

// General task class representing any type of task.
public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    private boolean getDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getDone()
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
