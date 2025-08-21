public enum Command {
    LIST("list"),
    EXIT("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public static Command fromString(String input) throws IllegalAccessException {
        for (Command c : Command.values()) {
            if (input.startsWith(c.getKeyword())) {
                return c;
            }
        }
        throw new IllegalAccessException("Unknown command: " + input);
    }
}
