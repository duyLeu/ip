package models;

// General task class representing any type of task.
public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public abstract String getType();

    public abstract String toStorage();

    @Override
    public String toString() {
        return this.isDone()
                ? "[X] " + this.name
                : "[ ] " + this.name;
    }
}
