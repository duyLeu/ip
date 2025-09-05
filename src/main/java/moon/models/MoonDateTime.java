package moon.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MoonDateTime {
    private final LocalDate date;
    private final Optional<LocalTime> time;

    private MoonDateTime(LocalDate date, Optional<LocalTime> time) {
        this.date = date;
        this.time = time;
    }

    public static MoonDateTime of(LocalDate d, LocalTime t) {
        return new MoonDateTime(d, Optional.of(t));
    }

    public static MoonDateTime ofDate(LocalDate d) {
        return new MoonDateTime(d, Optional.empty());
    }


    private String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getTime() {
        return this.time
                .map(t -> " " + t.format(DateTimeFormatter.ofPattern("HH:mm")))
                .orElse("");
    }

    @Override
    public String toString() {
        return this.getDate() + this.getTime();
    }
}