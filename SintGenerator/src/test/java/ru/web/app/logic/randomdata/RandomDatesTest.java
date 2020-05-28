package ru.web.app.logic.randomdata;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class RandomDatesTest {

    @Test
    public void createRandomIntBetween() {
    }

    @Test
    public void createRandomDate() {
        LocalDate localDate = RandomDates.createRandomDate(1900, 2020);
        Assert.assertNotNull(localDate);
        System.out.println(localDate);
    }
}