package command;

import exceptions.EmptyArgumentException;
import exceptions.ParseException;
import models.Event;
import models.MoonDateTime;
import models.Task;
import models.TaskList;
import ui.Ui;
import util.DateTimeParser;
import util.InputChecker;

public class AddEventCommand extends AddCommand {
    public static final Command COMMAND = Command.EVENT;

    public AddEventCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    public static Event parse(String input) throws ParseException, EmptyArgumentException {
        // split the string by the " /" separator into, supposedly, three parts: description, start time, end time
        String[] inputList = input.split(" /");

        InputChecker.checkCommandFormat(inputList, COMMAND);
        InputChecker.checkEmptyParameter(inputList[0], COMMAND, true);

        String eventName = inputList[0].substring(6).trim();
        String fromKeyword = inputList[1].split(" ")[0];
        String toKeyword = inputList[2].split(" ")[0];

        InputChecker.checkKeyword(fromKeyword, "from", COMMAND);
        InputChecker.checkKeyword(toKeyword, "to", COMMAND);
        InputChecker.checkEmptyParameter(fromKeyword, COMMAND, false);
        InputChecker.checkEmptyParameter(toKeyword, COMMAND, false);

        // extract the start-end times from the strings and return an Event object
        MoonDateTime fromTime = DateTimeParser.parse(inputList[1].substring(5), false);
        MoonDateTime toTime = DateTimeParser.parse(inputList[2].substring(3), false);
        return new Event(eventName, fromTime, toTime);
    }

    @Override
    public int getStatusCode() {
        return Command.EVENT.getStatusCode();
    }
}
