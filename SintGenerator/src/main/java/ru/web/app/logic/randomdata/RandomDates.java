package ru.web.app.logic.randomdata;

import java.time.LocalDate;

public class RandomDates {
    private static int createRandomIntBetween(final int start, final int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(final int startYear,
                                             final int endYear) {
        int day = createRandomIntBetween(1, Dates.DAYS.getValue());
        int month = createRandomIntBetween(1, Dates.MONTHS.getValue());
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
