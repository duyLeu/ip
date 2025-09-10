package moon.commands;

import moon.commands.enums.Command;
import moon.models.TaskList;

public class FindTaskCommand extends BaseCommand {
    private static final Command COMMAND = Command.FIND;
    private final String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    public int execute() {
        TaskList matchingTasks = this.getList().findByName(this.keyword);
        this.getUi().showTasksMatchedMessage(matchingTasks, this.keyword);
        return COMMAND.getStatusCode();
    }
}
