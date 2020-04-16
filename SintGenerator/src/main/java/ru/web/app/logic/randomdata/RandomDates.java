package ru.web.app.logic.randomdata;

import java.time.LocalDate;

public final class RandomDates {
    private static int createRandomIntBetween(final int start, final int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    /**
     * wrapper for internal generating dates method.
     * @param startYear
     * @param endYear
     * @return date in setting (inputting) range
     */
    public static LocalDate createRandomDate(final int startYear,
                                             final int endYear) {
        int day = createRandomIntBetween(1, Dates.DAYS.getValue());
        int month = createRandomIntBetween(1, Dates.MONTHS.getValue());
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    private RandomDates() {}
}
