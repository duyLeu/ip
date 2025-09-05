package command;

import ui.Ui;

public class ExitCommand extends BaseCommand {

    public ExitCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.EXIT.getStatusCode();
    }
}
