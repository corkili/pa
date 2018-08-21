package com.corkili.pa.validation.rule;

import java.util.Arrays;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.util.RangeUtil;

public abstract class IntRuleFactory {

    public static Rule valueRangeRule(String fieldName, IntConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        int[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName, Arrays.toString(values));
        }
        return new Rule(Integer.class, fieldName, describe);
    }

    public static Rule rangeRule(String fieldName, IntConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        IntRange[] ranges = constraint.ranges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is", fieldName));
            for (IntRange range : ranges) {
                describe.append(IUtils.format(" " +
                        RangeUtil.generateRangeFormatString(range.minInclude(), range.maxInclude()),
                        range.min(), range.max()));
            }

        }
        return new Rule(Integer.class, fieldName, describe.toString());
    }

}
