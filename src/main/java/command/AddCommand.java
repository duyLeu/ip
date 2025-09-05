package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public abstract class AddCommand extends BaseCommand {

    public AddCommand(String s) {
        super(s);
    }

    @Override
    public abstract int execute();

    @Override
    public abstract int getStatusCode();
}
