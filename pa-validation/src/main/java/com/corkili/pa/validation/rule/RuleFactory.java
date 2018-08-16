package com.corkili.pa.validation.rule;

import com.corkili.pa.common.util.IUtils;

public abstract class RuleFactory {

    public static <T> Rule FieldNullRule(Class<T> clazz, String fieldName) {
        return new Rule(clazz, fieldName, IUtils.format("\"{}\" is null", fieldName));
    }

}
