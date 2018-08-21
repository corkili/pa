package com.corkili.pa.validation.util;

import java.util.Arrays;

import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.DoubleValue;

public class DoubleUtil {

    public static String doubleValueToString(DoubleValue doubleValue) {
        return doubleValue.value() + "+-" + doubleValue.precision();
    }

    public static String doubleValueArrayToString(DoubleValue[] doubleValues) {
        String[] tmp = new String[doubleValues.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = "{}";
        }
        String[] doubleValueStrArr = new String[doubleValues.length];
        for (int i = 0; i < tmp.length; i++) {
            doubleValueStrArr[i] = doubleValueToString(doubleValues[i]);
        }
        return IUtils.format(Arrays.toString(tmp), (Object[]) doubleValueStrArr);
    }

    public static boolean equals(double a, double b, double precision) {
        return a == b || Math.abs(a - b) <= precision;
    }

    public static boolean less(double a, double b, double precision) {
        return a < b || Math.abs(a - b) <= precision;
    }

    public static boolean lessOrEquals(double a, double b, double precision) {
        return a <= b || Math.abs(a - b) <= precision;
    }

    public static boolean greater(double a, double b, double precision) {
        return a > b || Math.abs(a - b) <= precision;
    }

    public static boolean greaterOrEquals(double a, double b, double precision) {
        return a >= b || Math.abs(a - b) <= precision;
    }


}
