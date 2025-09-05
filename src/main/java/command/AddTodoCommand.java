package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.TODO.getStatusCode();
    }
}
