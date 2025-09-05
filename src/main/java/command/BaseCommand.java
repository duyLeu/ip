package command;

import models.Task;
import models.TaskList;
import ui.Ui;

public abstract class BaseCommand {
    private static final int ERROR_CODE = -1;
    private TaskList list;
    private Ui ui;
    private final String commandString;

    public BaseCommand(String s) {
        this.commandString = s;
    }

    public abstract int execute();

    public abstract int getStatusCode();

    public int getErrorCode() {
        return ERROR_CODE;
    }

    public void setMetaData(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    public String getCommandString() {
        return this.commandString;
    }

    public TaskList getList() {
        return this.list;
    }

    public Ui getUi() {
        return this.ui;
    }
}
