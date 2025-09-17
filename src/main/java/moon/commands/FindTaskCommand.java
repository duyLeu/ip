package moon.commands;

import moon.commands.enums.Command;
import moon.models.TaskList;

public class FindTaskCommand extends BaseCommand {
    private static final Command COMMAND = Command.FIND;
    private final String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute() {
        TaskList matchingTasks = this.getList().findByName(this.keyword);
        return getUi().showTasksMatchedMessage(matchingTasks, this.keyword);
    }
}
