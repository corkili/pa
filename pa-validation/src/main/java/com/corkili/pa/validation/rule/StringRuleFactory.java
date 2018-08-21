package com.corkili.pa.validation.rule;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.annotation.StringConstraint;
import com.corkili.pa.validation.util.RangeUtil;

public abstract class StringRuleFactory {

    public static Rule notEmptyRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        if (constraint.notEmpty()) {
            describe = IUtils.format("\"{}\" should not be empty", fieldName);
        } else {
            describe = IUtils.format("\"{}\" can be empty", fieldName);
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule lengthRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        if (constraint.length() < 0) {
            describe = IUtils.format("length of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("length of \"{}\" must equals {}", fieldName, constraint.length());
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule lengthRangeRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        StringBuilder describe;
        IntRange[] ranges = constraint.lengthRanges();
        if (ranges.length == 0) {
            describe = new StringBuilder(IUtils.format("length's range of \"{}\" is unlimited", fieldName));
        } else {
            describe = new StringBuilder(IUtils.format("length's range of \"{}\" is", fieldName));
            for (IntRange range : ranges) {
                describe.append(IUtils.format(" " +
                        RangeUtil.generateRangeFormatString(range.minInclude(), range.maxInclude()),
                        range.min(), range.max()));
            }

        }
        return new Rule(String.class, fieldName, describe.toString());
    }

    public static Rule valueRangeRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String[] values = constraint.valueRange();
        if (values.length == 0) {
            describe = IUtils.format("value of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("value of \"{}\" should in {}", fieldName, Arrays.toString(values));
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule containsRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String[] contains = constraint.contains();
        if (contains.length == 0) {
            describe = IUtils.format("substring of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("\"{}\" should contains substring in {}",
                    fieldName, Arrays.toString(contains));
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule notContainsRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String[] notContains = constraint.notContains();
        if (notContains.length == 0) {
            describe = IUtils.format("substring of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("\"{}\" should not contains substring in {}",
                    fieldName, Arrays.toString(notContains));
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule startWithRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String[] startWith = constraint.startWith();
        if (startWith.length == 0) {
            describe = IUtils.format("start of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("start of \"{}\" should in {}",
                    fieldName, Arrays.toString(startWith));
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule endWithRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String[] endWith = constraint.endWith();
        if (endWith.length == 0) {
            describe = IUtils.format("end of \"{}\" is unlimited", fieldName);
        } else {
            describe = IUtils.format("end of \"{}\" should in {}",
                    fieldName, Arrays.toString(endWith));
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule caseRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        boolean ignore = constraint.ignoreCase();
        boolean lower = constraint.lower();
        boolean upper = constraint.upper();
        if (ignore) {
            describe = IUtils.format("\"{}\" is case-insensitive", fieldName);
        } else if (lower && !upper) {
            describe = IUtils.format("\"{}\" should be lower case", fieldName);
        } else if (upper && !lower) {
            describe = IUtils.format("\"{}\" should be upper case", fieldName);
        } else {
            describe = IUtils.format("\"{}\" is case-insensitive", fieldName);
        }
        return new Rule(String.class, fieldName, describe);
    }

    public static Rule regexRule(String fieldName, StringConstraint constraint) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String regex = constraint.regex();
        if (StringUtils.isBlank(regex)) {
            describe = IUtils.format("\"{}\" is not unlimited by regexRule", fieldName);
        } else {
            describe = IUtils.format("\"{}\" should be conform to regexRule \"{}\"", fieldName, regex);
        }
        return new Rule(String.class, fieldName, describe);
    }

}
