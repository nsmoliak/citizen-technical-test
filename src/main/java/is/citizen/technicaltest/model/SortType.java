package is.citizen.technicaltest.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SortType {
    ASC, DESC;

    public static String getValuesString() {
        return ASC + " & " + DESC;
    }

    public static boolean contains(String test) {
        return Arrays.stream(SortType.values()).anyMatch(c -> c.name().equals(test));
    }
}