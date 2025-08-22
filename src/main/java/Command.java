import java.util.Arrays;
import java.util.stream.*;
import java.util.Optional;

public enum Command {
    EXIT("bye", "bye", 0), // status code 1 is default for running
    LIST("list", "list", 2),
    MARK("mark", "mark {task number}", 4),
    UNMARK("unmark", "unmark {task number}", 5),
    TODO("todo", "todo {task description}", 6),
    DEADLINE("deadline", "deadline {task description} \\by {deadline time}", 7),
    EVENT("event", "event {task description} \\from {start time} \\by {end time}", 8),
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

    public static Stream<Command> getCommandStream() {
        return Arrays.stream(Command.values());
    }

    public static Command fromString(String input) throws InvalidCommandException {
        String inputKeyword = input.split(" ")[0];
        Optional<Command> optionalCommand = Command.getCommandStream()
                .filter(c -> c.getKeyword().equals(inputKeyword))
                .findFirst();
        return optionalCommand
                .orElseThrow(() -> new InvalidCommandException(inputKeyword, "Wuf? I don't recognize this"));
    }
}
