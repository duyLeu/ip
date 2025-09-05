package moon.parser.storage;

import moon.models.Todo;
import moon.parser.exceptions.ParseException;

public class TodoStorageParser implements StorageParser<Todo> {
    public Todo parse(String[] inputs) throws ParseException {
        String name = inputs[2];
        boolean done = inputs[1].equals("1");

        Todo todo = new Todo(name);
        todo.setDone(done);
        return todo;
    }
}
