package com.corkili.pa.validation.rule;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.DoubleConstraint;
import com.corkili.pa.validation.annotation.DoubleRange;
import com.corkili.pa.validation.annotation.DoubleValue;
import com.corkili.pa.validation.util.DoubleUtil;
import com.corkili.pa.validation.util.RangeUtil;

public abstract class DoubleRuleFactory {

    public static Rule valueRangeRule(String fieldName, DoubleConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        DoubleValue[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName,
                    DoubleUtil.doubleValueArrayToString(values));
        }
        return new Rule(Double.class, fieldName, describe);
    }

    public static Rule rangeRule(String fieldName, DoubleConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        DoubleRange[] ranges = constraint.ranges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is", fieldName));
            for (DoubleRange range : ranges) {
                describe.append(IUtils.format(" " +
                                RangeUtil.generateRangeFormatString(range.minInclude(), range.maxInclude()),
                        DoubleUtil.doubleValueToString(range.min()), DoubleUtil.doubleValueToString(range.max())));
            }
        }
        return new Rule(Double.class, fieldName, describe.toString());
    }
    
}
