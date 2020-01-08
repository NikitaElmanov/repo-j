package ru.web.app.logic.randomdata;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Random;

import static ru.web.app.logic.randomdata.RandomDates.createRandomDate;

public class Provider {

    // function to generate a random string of length n
    public static String getRandomString(Integer n) {
        if (n < 1) throw new IllegalArgumentException();

        Integer max = n, min = 1;

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        for (int i = 0; i < randomNum; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                              .charAt(index));
        }

        return sb.toString();
    }

    public static Integer getRandomInteger(Integer max, Integer min) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static Date getRandomDate(Integer start, Integer end) {
        if (start >= end) {
            throw new IllegalArgumentException("end must be greater than start");
        }
        LocalDate randomDate = createRandomDate(start, end);
        return Date.valueOf(randomDate);
    }

    public static String getRandomDouble(Double rightLimit, Integer afterComma) {

        Double leftLimit = 1D;

        StringBuilder format = new StringBuilder("#0.");

        for (int i = 0; i < afterComma; i++){
            format.append("0");
        }

        Double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);

        NumberFormat formatter = new DecimalFormat(format.toString());
        String generatedDoubleStr = formatter.format(generatedDouble);

        return generatedDoubleStr;
    }
}
