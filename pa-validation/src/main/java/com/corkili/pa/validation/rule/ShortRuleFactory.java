package com.corkili.pa.validation.rule;

import java.util.Arrays;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.ShortConstraint;
import com.corkili.pa.validation.annotation.ShortRange;
import com.corkili.pa.validation.util.RangeUtil;

public abstract class ShortRuleFactory {

    public static Rule valueRangeRule(String fieldName, ShortConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        short[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName, Arrays.toString(values));
        }
        return new Rule(Short.class, fieldName, describe);
    }

    public static Rule rangeRule(String fieldName, ShortConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        ShortRange[] ranges = constraint.ranges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is", fieldName));
            for (ShortRange range : ranges) {
                describe.append(IUtils.format(" " +
                        RangeUtil.generateRangeFormatString(range.minInclude(), range.maxInclude()),
                        range.min(), range.max()));
            }
        }
        return new Rule(Short.class, fieldName, describe.toString());
    }

}
