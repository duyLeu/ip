import java.util.Arrays;
import java.util.stream.*;
import java.util.Optional;

public enum Command {
    EXIT("bye", 0), // status code 1 is default for running
    LIST("list", 2),
    MARK("mark", 4),
    UNMARK("unmark", 5),
    TODO("todo", 6),
    DEADLINE("deadline", 7),
    EVENT("event", 8);

    private final String keyword;
    private final int statusCode;

    Command(String keyword, int statusCode) {
        this.keyword = keyword;
        this.statusCode = statusCode;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public static Command fromString(String input) throws IllegalAccessException {
        String inputKeyword = input.split(" ")[0];
        Stream<Command> commandStream = Arrays.stream(Command.values());
        Optional<Command> optionalCommand = commandStream
                .filter(c -> c.getKeyword().equals(inputKeyword))
                .findFirst();
        return optionalCommand.orElseThrow(() -> new IllegalAccessException("Unknown command: " + input));
    }
}
