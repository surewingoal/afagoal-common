package com.afagoal.utils.num;

import java.text.DecimalFormat;

/**
 * Created by BaoCai on 18/5/27.
 * Description:
 */
public class NumUtils {
    private static final DecimalFormat NUM_FORMAT = new DecimalFormat("#.00000");
    public static final String UNIT_USD = "USD";
    public static final String UNIT_ETH = "ETH";

    public static String moneyFormat(Number number) {
        return moneyFormat(number, UNIT_USD);
    }

    public static String moneyFormat(Number number, String unit) {
        if (null == number) {
            throw new IllegalArgumentException("number can be null!");
        }
        return NUM_FORMAT.format(number) + " " + unit;
    }

}
