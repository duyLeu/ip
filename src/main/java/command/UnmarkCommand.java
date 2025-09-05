package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public class UnmarkCommand extends BaseCommand {

    public UnmarkCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.MARK.getStatusCode();
    }
}
