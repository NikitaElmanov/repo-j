package ru.web.app.logic.randomdata;

public enum Dates {
    DAYS(28),
    MONTHS(12);

    private final int someInt;
    Dates(final int someInt) {
        this.someInt = someInt;
    }

    public int getValue() {
        return someInt;
    }
}
