package ru.web.app.logic.randomdata;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {

    @Test
    public void getRandomString() {
        System.out.println(Provider.getRandomString(205));
    }
    @Test
    public void getRandomInteger(){
        System.out.println(Provider.getRandomInteger(233,4));
    }

    @Test
    public void getRandomDate(){
        System.out.println(Provider.getRandomDate(1700, 2000));
    }

    @Test
    public void getRandomDouble(){
        System.out.println(Provider.getRandomDouble(5000D, 3));
    }
}