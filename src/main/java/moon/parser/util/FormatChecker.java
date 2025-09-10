package moon.parser.util;

import moon.commands.enums.Command;
import moon.models.TaskList;
import moon.parser.exceptions.EmptyArgumentException;
import moon.parser.exceptions.InvalidIndexException;
import moon.parser.exceptions.ParseException;

public class FormatChecker {
    public static void checkCommandFormat(String[] inputList, Command command) throws ParseException {
        int numOfParameters = switch (command) {
        case TODO -> 1;
        case DEADLINE -> 2;
        case EVENT -> 3;
        default -> throw new ParseException(command,
                "Wuf! Are you sure you have the correct command?");
        };

        if (inputList.length < numOfParameters) {
            throw new ParseException(command,
                    "Wuf! Are you missing a dash '/' or a command somewhere?");
        } else if (inputList.length > numOfParameters) {
            throw new ParseException(command,
                    "Wuf! Are you sure you have the correct command?");
        }
    }

    public static void checkEmptyParameter(String inputString, Command command, boolean isTaskName)
            throws ParseException {

        String exceptionMessage = isTaskName
                ? "Wuf! Your task name cannot be empty!"
                : switch (command) {
        case DEADLINE -> "Wuf! Your deadline time cannot be empty!";
        case EVENT -> "Wuf! Both your start and end time cannot be empty!";
        default -> throw new ParseException(command,
                "Wuf! Are you sure you have the correct command?");
        };

        if (inputString.split(" ").length <= 1) {
            System.out.println(inputString);
            throw new EmptyArgumentException(command,
                    exceptionMessage);
        }
    }

    public static void checkKeyword(String actualInput, String expectedInput, Command command) throws ParseException {
        if (!actualInput.equals(expectedInput)) {
            throw new ParseException(command,
                    String.format("Wuf! I think you make a mistake here: '%s'", actualInput));
        }
    }

    public static void throwExceptionIfOutOfIndex(int index, TaskList list) throws InvalidIndexException {
        if (index < 0 || index >= list.size()) { // check for index range
            throw new InvalidIndexException(Command.DELETE,
                    "Wuf! Your index is out of range!");
        }
    }
}
