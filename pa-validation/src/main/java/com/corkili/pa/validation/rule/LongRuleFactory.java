package com.corkili.pa.validation.rule;

import java.util.Arrays;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.LongConstraint;
import com.corkili.pa.validation.annotation.LongRange;

public abstract class LongRuleFactory {

    public static Rule valueRangeRule(String fieldName, LongConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        long[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName, Arrays.toString(values));
        }
        return new Rule(Long.class, fieldName, describe);
    }

    public static Rule rangeRule(String fieldName, LongConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        LongRange[] ranges = constraint.ranges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is", fieldName));
            for (LongRange range : ranges) {
                describe.append(IUtils.format(" [{}, {}]", range.min(), range.max()));
            }

        }
        return new Rule(Long.class, fieldName, describe.toString());
    }

}
