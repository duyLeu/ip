package util;

import exception.MoonException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Optional;

import exception.ParseException;
import model.MoonDateTime;

public class DateTimeParser {
    private static final DateTimeFormatter DATE     = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter MOONDATE     = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter MOONDATETIME = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");


    public static MoonDateTime parse(String input, boolean isFromStorage) throws ParseException {
        return tryParseDateTime(input, isFromStorage)
                .map(dt -> MoonDateTime.of(dt.toLocalDate(), dt.toLocalTime()))
                .or(() -> tryParseDate(input, isFromStorage).map(MoonDateTime::ofDate))
                .orElseThrow(() -> new ParseException("Could not parse date/time: " + input));
    }

    private static Optional<LocalDateTime> tryParseDateTime(String s, boolean isFromStorage) {
        DateTimeFormatter format = isFromStorage ? MOONDATETIME : DATETIME;
        try {
            LocalDateTime dt = LocalDateTime.parse(s, format);
            return Optional.of(dt);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    private static Optional<LocalDate> tryParseDate(String s, boolean isFromStorage) {
        DateTimeFormatter format = isFromStorage ? MOONDATE : DATE;
        try {
            LocalDate d = LocalDate.parse(s, format);
            return Optional.of(d);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
