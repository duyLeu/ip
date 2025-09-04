package util;

import exception.MoonException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

import model.MoonDateTime;

public class DateTimeParser {
    private static final DateTimeFormatter DATE     = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static MoonDateTime parse(String input) throws MoonException {
        return tryParseDateTime(input)
                .map(dt -> MoonDateTime.of(dt.toLocalDate(), dt.toLocalTime()))
                .or(() -> tryParseDate(input).map(MoonDateTime::ofDate))
                .orElseThrow(() -> new MoonException("Could not parse date/time: " + input));
    }

    public static Optional<LocalDateTime> tryParseDateTime(String s) {
        try {
            LocalDateTime dt = LocalDateTime.parse(s, DATETIME);
            return Optional.of(dt);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    public static Optional<LocalDate> tryParseDate(String s) {
        try {
            LocalDate d = LocalDate.parse(s, DATE);
            return Optional.of(d);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
