package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.IntRuleFactory;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.validator.ValidateResult;

public class IntegerValidator extends AbstractValidator<Integer, IntConstraint> {

    private static IntegerValidator instance;
    
    static IntegerValidator getInstance() {
        if (instance == null) {
            synchronized (IntegerValidator.class) {
                if (instance == null) {
                    instance = new IntegerValidator();
                }
            }
        }
        return instance;
    }

    private IntegerValidator() {
    }

    @Override
    protected Class<?> getValidatorClass() {
        return IntegerValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Integer> getValidateElementType() {
        return Integer.class;
    }

    @Override
    public Class<IntConstraint> getAnnotationType() {
        return IntConstraint.class;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, int element,
                                       IntConstraint constraint, ValidateResult result) {
        Rule rule = IntRuleFactory.valueRangeRule(fieldName, constraint);
        int[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (int value : values) {
                if (element == value) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRange(String fieldName, int element,
                                        IntConstraint constraint, ValidateResult result) {
        Rule rule = IntRuleFactory.rangeRule(fieldName, constraint);
        boolean success = true;
        IntRange[] ranges = constraint.ranges();
        if (ranges.length != 0) {
            for (IntRange range : ranges) {
                success = success && range.min() <= element && element <= range.max();
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

}
