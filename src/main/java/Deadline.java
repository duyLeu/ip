public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return this.deadline;
    }

    public static Deadline fromString(String input) throws ParseException, EmptyArgumentException {
        String[] inputList = input.split(" /");
        if (inputList.length != 2) {
            throw new ParseException(input, Command.DEADLINE,
                    "Wuf! are you missing a dash '/' somewhere?");
        }

        String deadlineName = inputList[0].substring(9);

        String byKeyword = inputList[1].split(" ")[0];
        if (!byKeyword.equals("by")) {
            throw new ParseException(byKeyword, Command.DEADLINE,
                    String.format("Wuf! I think you make a mistake here: '%s'", byKeyword));

        } else if (inputList[1].split(" ").length <= 1) {
            throw new EmptyArgumentException(Command.DEADLINE,
                    "Wuf! Your deadline time cannot be empty!");
        }

        String deadlineTime = inputList[1].substring(3);

        return new Deadline(deadlineName, deadlineTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
