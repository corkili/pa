package com.corkili.pa.validation.util;

public abstract class RangeUtil {

    public static String generateRangeFormatString(boolean leftInclude, boolean rightInclude) {
        StringBuilder sb = new StringBuilder();
        if (leftInclude) {
            sb.append("[");
        } else {
            sb.append("(");
        }
        sb.append("{}, {}");
        if (rightInclude) {
            sb.append("]");
        } else {
            sb.append(")");
        }
        return sb.toString();
    }

}
