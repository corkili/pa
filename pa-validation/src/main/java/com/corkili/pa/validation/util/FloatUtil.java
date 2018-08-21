package com.corkili.pa.validation.util;

import java.util.Arrays;

import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.FloatValue;

public abstract class FloatUtil {

    public static String floatValueToString(FloatValue floatValue) {
        return floatValue.value() + "+-" + floatValue.precision();
    }

    public static String floatValueArrayToString(FloatValue[] floatValues) {
        String[] tmp = new String[floatValues.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = "{}";
        }
        String[] floatValueStrArr = new String[floatValues.length];
        for (int i = 0; i < tmp.length; i++) {
            floatValueStrArr[i] = floatValueToString(floatValues[i]);
        }
        return IUtils.format(Arrays.toString(tmp), (Object[]) floatValueStrArr);
    }

    public static boolean equals(float a, float b, float precision) {
        return a == b || Math.abs(a - b) <= precision;
    }

    public static boolean less(float a, float b, float precision) {
        return a < b || Math.abs(a - b) <= precision;
    }

    public static boolean lessOrEquals(float a, float b, float precision) {
        return a <= b || Math.abs(a - b) <= precision;
    }

    public static boolean greater(float a, float b, float precision) {
        return a > b || Math.abs(a - b) <= precision;
    }

    public static boolean greaterOrEquals(float a, float b, float precision) {
        return a >= b || Math.abs(a - b) <= precision;
    }

}
