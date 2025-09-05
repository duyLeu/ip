package moon.commands;

import moon.models.Task;

public abstract class AddCommand extends BaseCommand {
    public void addToStorage(Task task) {
        super.getList().add(task);
    }
}
