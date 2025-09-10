package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import moon.parser.exceptions.ParseException;
import moon.parser.usercommand.AddDeadlineCommandParser;

public class AddDeadlineCommandParserTest {
    @Test
    public void parse_emptyNameArgument_exceptionThrown() {
        try {
            AddDeadlineCommandParser parser = new AddDeadlineCommandParser();
            parser.parse("deadline ");
        } catch (ParseException e) {
            assertEquals("Wuf! Your task name cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyTimeArgument_exceptionThrown() {
        try {
            AddDeadlineCommandParser parser = new AddDeadlineCommandParser();
            parser.parse("deadline read books /by");
        } catch (ParseException e) {
            assertEquals("Wuf! Your deadline time cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_wrongByKeyword_exceptionThrown() {
        try {
            AddDeadlineCommandParser parser = new AddDeadlineCommandParser();
            parser.parse("deadline read books /bo 15/03/2025");
        } catch (ParseException e) {
            assertEquals("Wuf! I think you make a mistake here: 'bo'", e.getMessage());
        }
    }

    @Test
    public void parse_wrongTimeFormat_exceptionThrown() {
        try {
            AddDeadlineCommandParser parser = new AddDeadlineCommandParser();
            parser.parse("deadline read books /by 2025-03-15");
        } catch (ParseException e) {
            assertEquals("Could not parse date/time: 2025-03-15", e.getMessage());
        }
    }
}
