package com.corkili.pa.validation.rule;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.FloatConstraint;
import com.corkili.pa.validation.annotation.FloatRange;
import com.corkili.pa.validation.annotation.FloatValue;
import com.corkili.pa.validation.util.FloatUtil;
import com.corkili.pa.validation.util.RangeUtil;

public abstract class FloatRuleFactory {

    public static Rule valueRangeRule(String fieldName, FloatConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        FloatValue[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName,
                    FloatUtil.floatValueArrayToString(values));
        }
        return new Rule(Float.class, fieldName, describe);
    }

    public static Rule rangeRule(String fieldName, FloatConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        FloatRange[] ranges = constraint.ranges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("range of \"{}\" is", fieldName));
            for (FloatRange range : ranges) {
                describe.append(IUtils.format(" " +
                        RangeUtil.generateRangeFormatString(range.minInclude(), range.maxInclude()),
                        FloatUtil.floatValueToString(range.min()), FloatUtil.floatValueToString(range.max())));
            }
        }
        return new Rule(Float.class, fieldName, describe.toString());
    }
    
}
