package ru.web.app.logic.randomdata;

public enum Dates {
    DAYS(28),
    MONTHS(12);

    /**
     * variable contains some day or month number.
     */
    private final int someInt;

    /**
     * container initializes {@link someInt} variable.
     * @param someInt
     */
    Dates(final int someInt) {
        this.someInt = someInt;
    }

    /**
     * getter method to provide {@link someInt} variable.
     * @return
     */
    public int getValue() {
        return someInt;
    }
}
