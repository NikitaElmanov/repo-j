package ru.web.app.logic.randomdata;

import org.junit.Test;

public class ProviderTest {

    @Test
    public void getRandomString() {
        for (int i = 0 ; i < 30; i++) {
            System.out.println(Provider.getRandomString(50, false));
        }
    }
    @Test
    public void getRandomInteger() {
        for (int i = 0; i < 30; i++) {
            System.out.println(Provider.getRandomInteger(-1000,500, false));
        }
    }

    @Test
    public void getRandomDate(){
        for (int i = 0; i < 30; i++) {
            System.out.println(Provider.getRandomDate(1700, 2000, false));
        }
    }

    @Test
    public void getRandomDouble() {
        for (int i = 0; i < 30; i++) {
            System.out.println(Provider.getRandomDouble(1000D, 3, false));
        }
    }

    @Test
    public void getRandomBoolean() {
        for (int i = 0 ; i < 10; i++) {
            System.out.println(Provider.getRandomBoolean(false));
        }
    }

    @Test
    public void getRandomStringAsPK() {
        Provider.getRandomStringAsPK(100, "10");
    }
}