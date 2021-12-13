package com.yahui;

import java.math.BigDecimal;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-08-31
 */
public class Main {
    private static final int NUM_1W = 10000;
    public static String getDisplayText(long value) {
        if (value < NUM_1W) {
            return String.valueOf(value);
        }
        BigDecimal bd = BigDecimal.valueOf(((double) value) / NUM_1W);
        String p = String.valueOf(bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
        return p + "万";
    }

    // CHECKSTYLE:OFF
    public static void main(String[] args) {
//        System.out.println(getDisplayText(1000));
//        System.out.println(getDisplayText(10712));
//        System.out.println(getDisplayText(10515));
//        System.out.println(getDisplayText(10415));
//        System.out.println(getDisplayText(10117));

//        IntStream.range(0, 100).forEach(i -> {
//            System.out.println(((double) (Long.MAX_VALUE - System.currentTimeMillis())) / Long.MAX_VALUE);
//        });



    }
    // CHECKSTYLE:ON
}
