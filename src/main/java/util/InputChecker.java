package util;

import exception.EmptyArgumentException;
import exception.ParseException;

import model.Command;

public class InputChecker {
    public static void checkCommandFormat(String[] inputList, Command command) throws ParseException {
        int numOfParameters = switch (command) {
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
            throws ParseException, EmptyArgumentException {

        String exceptionMessage = isTaskName
                ? "Wuf! Your task name cannot be empty!"
                : switch (command) {
                    case DEADLINE -> "Wuf! Your deadline time cannot be empty!";
                    case EVENT -> "Wuf! Both your start and end time cannot be empty!";
                    default -> throw new ParseException(command,
                            "Wuf! Are you sure you have the correct command?");
                };

        if (inputString.split(" ").length <= 1) {
            throw new EmptyArgumentException(command,
                    exceptionMessage);

        }
    }

    public static void checkKeyword(String actualInput, String expectedInput, Command command) throws ParseException {
        if (!actualInput.equals(expectedInput)) {
            throw new ParseException(actualInput, command,
                    String.format("Wuf! I think you make a mistake here: '%s'", actualInput));
        }
    }
}
