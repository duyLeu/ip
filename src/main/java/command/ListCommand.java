package command;

public class ListCommand extends BaseCommand {

    public ListCommand(String s) {
        super(s);
    }

    @Override
    public int execute() {

    }

    @Override
    public int getStatusCode() {
        return Command.LIST.getStatusCode();
    }
}
