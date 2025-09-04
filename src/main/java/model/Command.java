package model;

import exception.InvalidCommandException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.*;

// Command enum containing all the
public enum Command {
    // status code 1 is default for starting the chatbot

    // each enum object has a keyword used by the program to identify which method to route to,
    // format to output to the user in case of error
    // and status code to return to the backend
    EXIT("bye", "bye", 0),
    LIST("list", "list", 2),
    MARK("mark", "mark {task number}", 4),
    UNMARK("unmark", "unmark {task number}", 5),
    TODO("todo", "todo {task description}", 6),
    DEADLINE("deadline", "deadline {task description} /by {deadline time}", 7),
    EVENT("event", "event {task description} /from {start time} /by {end time}", 8),
    DELETE("delete", "delete {task number}", 9);

    private final String keyword;
    private final String format;
    private final int statusCode;

    Command(String keyword, String format, int statusCode) {
        this.keyword = keyword;
        this.format = format;
        this.statusCode = statusCode;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getFormat() {
        return this.format;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    // return a stream version of the list of Command objects.
    public static Stream<Command> getCommandStream() {
        return Arrays.stream(Command.values());
    }

    public static Command findMatchingCommand(String input) throws InvalidCommandException {
        String inputKeyword = input.split(" ")[0];
        // Use stream and optional for cleaner code and more functionalities (i.e. filter, findFirst)
        Optional<Command> optionalCommand = Command.getCommandStream()
                .parallel()
                .filter(c -> c.getKeyword().equals(inputKeyword))
                .findFirst();
        return optionalCommand
                .orElseThrow(() -> new InvalidCommandException(inputKeyword,
                        "Wuf? Are you sure you have the correct command?"));
    }
}