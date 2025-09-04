package util;

import exception.InvalidCommandException;
import exception.ParseException;

import model.*;

public class InputParser {
    public static Task parseFromStorage(String input) throws ParseException, InvalidCommandException {
        return switch (input.substring(0, 1)) {
            case "T" -> Todo.parseFromStorage(input);
            case "D" -> Deadline.parseFromStorage(input);
            case "E" -> Event.parseFromStorage(input);
            default -> throw new InvalidCommandException("Command not found");
        };
    }
}
