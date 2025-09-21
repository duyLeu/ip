package moon.models;

import java.util.ArrayList;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * Provides basic operations for adding, retrieving, deleting,
 * and checking tasks. Serves as the in-memory storage structure
 * for the Moon chatbot.
 *
 * @see Task
 * @see Todo
 * @see Deadline
 * @see Event
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task Task to be appended
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index Zero-based index of the task to return
     * @return Task at the specified position
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in TaskList.get()";
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param index Zero-based index of the task to remove
     * @return The removed task
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in TaskList.delete()";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return Number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether this task list is empty.
     *
     * @return {@code true} if the list contains no tasks, {@code false} otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds all tasks whose names contain the given substring, ignoring case.
     * <p>
     * If the search string is empty, an empty {@link TaskList} is returned.
     * Matching is done using {@link String#toLowerCase()} on both the task name and the query.
     *
     * @param s the substring to search for
     * @return a new {@link TaskList} containing all tasks whose names include {@code s}, case-insensitive
     */
    public TaskList findByName(String s) {
        if (s.isEmpty()) {
            return new TaskList();
        }
        String query = s.toLowerCase();
        TaskList tempList = new TaskList();
        this.tasks.stream()
                .filter(task -> task.getName().toLowerCase().contains(query))
                .forEach(tempList::add);
        return tempList;
    }

    /**
     * Returns a formatted string representation of this task list
     * for display to the user.
     * <p>
     * Format:
     * <pre>
     *   1. [T][ ] read book
     *   2. [D][X] return book (by: June 6th)
     *   3. [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     * </pre>
     *
     * @return Formatted string of tasks with indices
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("  %d. %s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }
}
