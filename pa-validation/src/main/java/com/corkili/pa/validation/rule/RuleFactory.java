package com.corkili.pa.validation.rule;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.exception.ValidationException;

public abstract class RuleFactory {

    public static <T> Rule FieldNullRule(Class<T> clazz, String fieldName) {
        return new Rule(clazz, fieldName, IUtils.format("\"{}\" is null", fieldName));
    }

    public static <T> Rule notNullRule(String fieldName, T constraint, Class<?> elementType) {
        if (CheckUtils.hasNull(fieldName, constraint)) {
            return Rule.EMPTY_RULE;
        }
        try {
            String describe;
            if ((boolean)constraint.getClass().getMethod("notNull").invoke(constraint)) {
                describe = IUtils.format("\"{}\" should not be null", fieldName);
            } else {
                describe = IUtils.format("\"{}\" can be null", fieldName);
            }
            return new Rule(elementType, fieldName, describe);
        } catch (Exception e) {
            throw new ValidationException(
                    IUtils.format("class \"{}\" not contains method \"boolean notNull();\"",
                            constraint.getClass().getName()), e);
        }
    }

}
