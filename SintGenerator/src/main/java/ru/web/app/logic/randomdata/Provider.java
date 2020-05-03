package ru.web.app.logic.randomdata;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.Stack;

import static ru.web.app.logic.randomdata.RandomDates.createRandomDate;

public final class Provider {

    /**
     * default constant (range) to generate
     * string PK {@link getRandomStringAsPK}.
     */
    private static int DEFAULT_RANGE = 50;
    /**
     * default constants (min and max range)
     * to generate integer PK {@link getRandomIntegerAsPK}.
     */
    private static final int DEFAULT_RANGE_INTEGER_MIN = 1;
    private static final int DEFAULT_RANGE_INTEGER_MAX = 100;

    /**
     * @param amountRows
     * @return stack of generated integer PKs.
     */
    public static Stack<String> getRandomIntegerAsPK(final int amountRows) {
        Stack<String> genPKs = new Stack<>();
        String genPK;

        for (int i = 0; i < amountRows; i++) {
            genPK = String.valueOf(getRandomInteger(DEFAULT_RANGE_INTEGER_MIN,
                                                    DEFAULT_RANGE_INTEGER_MAX,
                                                    false));

            while (genPKs.contains(genPK)) {
                genPK = String.valueOf(getRandomInteger(DEFAULT_RANGE_INTEGER_MIN,
                                                        DEFAULT_RANGE_INTEGER_MAX,
                                                        false));
            }

            genPKs.add(genPK);
        }

        return genPKs;
    }

    /**
     * @param amountRows
     * @return stack of generated string PKs.
     */
    public static Stack<String> getRandomStringAsPK(final int amountRows,
                                                    final String range) {
        Stack<String> genPKs = new Stack<>();
        String genPK;

        if (range != null || !range.isEmpty()) {

            DEFAULT_RANGE = Integer.parseInt(range);
        }

        for (int i = 0; i < amountRows; i++) {
            genPK = getRandomString(DEFAULT_RANGE, false);

            while (genPKs.contains(genPK)) {
                genPK = getRandomString(DEFAULT_RANGE, false);
            }

            genPKs.add(genPK);
        }

        return genPKs;
    }

    /**
     * function to generate a random string of length n.
     */
    public static String getRandomString(final Integer n, final boolean nullFlag) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        Integer max = n;
        Integer min = 1;

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        Random rand = new Random();

        //null values handler
        if (nullFlag && rand.nextInt() > 0.5) {
            return null;
        }

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

    /**
     * @param min
     * @param max
     * @return random figure from inputting range.
     */
    public static Integer getRandomInteger(final Integer min,
                                           final Integer max,
                                           final boolean nullFlag) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        int randomNum = r.nextInt();
        if (nullFlag && randomNum > 0.5) {
            return null;
        }

        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * @param start
     * @param end
     * @return random date from inputting range.
     */
    public static Date getRandomDate(final Integer start,
                                     final Integer end,
                                     final boolean nullFlag) {
        if (start >= end) {
            throw new IllegalArgumentException("end must be greater than start");
        }

        if (nullFlag && Math.random() > 0.5){
            return null;
        }
        LocalDate randomDate = createRandomDate(start, end);
        return Date.valueOf(randomDate);
    }

    /**
     * @param rightLimit
     * @param afterComma
     * @return random double figure with inputting settings.
     */
    public static String getRandomDouble(final Double rightLimit,
                                         final Integer afterComma,
                                         final boolean nullFlag) {

        if (nullFlag && Math.random() > 0.5) {
            return null;
        }

        Double leftLimit = 1D;

        StringBuilder format = new StringBuilder("#0.");

        for (int i = 0; i < afterComma; i++) {
            format.append("0");
        }

        Double generatedDouble = leftLimit
                + new Random().nextDouble()
                * (rightLimit - leftLimit);

        NumberFormat formatter = new DecimalFormat(format.toString());
        String generatedDoubleStr = formatter.format(generatedDouble)
                .replace(",", ".");

        return generatedDoubleStr;
    }

    /**
     * @return true or false.
     */
    public static Boolean getRandomBoolean(final boolean nullFlag) {
        if (nullFlag && Math.random() < 0.5) {
            return null;
        }

        return Math.random() < 0.5;
    }

    private Provider(){}
}
