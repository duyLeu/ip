package moon.parser.util;

import java.util.Arrays;

public class ExtractString {
    public static String extract(String input, String exclude) {
        return Arrays.stream(input.split("\\s+"))
                .filter(s -> !s.equals(exclude))
                .reduce("", (s1, s2) -> s1 + " " + s2)
                .trim();
    }
}
