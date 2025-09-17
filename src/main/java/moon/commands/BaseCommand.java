package moon.commands;

import moon.models.TaskList;
import moon.parser.exceptions.ParseException;
import moon.ui.UiMessages;

/**
 * Abstract base class for all commands in the Moon chatbot.
 * <p>
 * Provides common metadata (task list and UI) and a shared error code.
 * Each concrete command must implement {@link #execute()}.
 */
public abstract class BaseCommand {
    private TaskList list;
    private UiMessages uiMessages;

    /**
     * Executes this command.
     *
     * @return confirmation message to be displayed to the user
     * @throws ParseException If the input could not be parsed properly
     */
    public abstract String execute() throws ParseException;

    /**
     * Sets metadata needed by this command before execution.
     *
     * @param list The current task list
     * @param uiMessages   The UI instance for interaction
     */
    public void setMetaData(TaskList list, UiMessages uiMessages) {
        this.list = list;
        this.uiMessages = uiMessages;
    }

    /**
     * Returns the task list associated with this command.
     *
     * @return Task list
     */
    public TaskList getList() {
        return this.list;
    }

    /**
     * Returns the UI instance associated with this command.
     *
     * @return UI
     */
    public UiMessages getUi() {
        return this.uiMessages;
    }
}
