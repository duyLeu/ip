package moon.commands;

import moon.models.TaskList;
import moon.parser.exceptions.ParseException;
import moon.ui.Ui;

public abstract class BaseCommand {
    private static final int ERROR_CODE = -1;
    private TaskList list;
    private Ui ui;

    public abstract int execute() throws ParseException;

    public int getErrorCode() {
        return ERROR_CODE;
    }

    public void setMetaData(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    public TaskList getList() {
        return this.list;
    }

    public Ui getUi() {
        return this.ui;
    }
}
