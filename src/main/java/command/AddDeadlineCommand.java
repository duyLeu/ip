package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.DEADLINE.getStatusCode();
    }
}
