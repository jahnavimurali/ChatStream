package com.chat.streams.generation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDataGenUtility {
    /**
     * @Description: Randomly generate latitude and longitude within a rectangle
     * @param MinLon: minimum longitude MaxLon: maximum longitude MinLat: minimum
     *                latitude MaxLat: maximum latitude
     * @return @throws
     */
    public static Map<String, String> randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 6 digits after the decimal
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        Map<String, String> map = new HashMap<String, String>();
        map.put("J", lon);
        map.put("W", lat);
        return map;
    }

    public static String randomElement(String[] array) {
        Random r = new Random();
        return array[r.nextInt(array.length)];
    }

    public static String randomPhoneno() {
        Random generator = new Random();
        String strippedNum;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        num1 = generator.nextInt(600) + 100;// numbers can't include an 8 or 9, can't go below 100.
        num2 = generator.nextInt(641) + 100;// number has to be less than 742//can't go below 100.
        num3 = generator.nextInt(8999) + 1000; // make numbers 0 through 9 for each digit.//can't go below 1000.

        strippedNum = Integer.toOctalString(num1);

        return strippedNum + "-" + num2 + "-" + num3;
    }

    public static double randomBetween(int min, int max) {
        return Math.random() * (max - min) + min;
    }

    public static int randomIntBetween(int min, int max) {
        return (int)(Math.random() * (max - min) + min);

    }
}

