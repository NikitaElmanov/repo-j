package ru.web.app.logic.randomdata;

public enum Dates {
    DAYS   (28),
    MONTHS (12);

    private final int someint;
    Dates(int someint) {
        this.someint = someint;
    }

    public int getValue() {
        return someint;
    }
}
