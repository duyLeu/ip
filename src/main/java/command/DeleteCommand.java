package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public class DeleteCommand extends BaseCommand {

    public DeleteCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.DELETE.getStatusCode();
    }
}
