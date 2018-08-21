package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.validation.annotation.LongConstraint;
import com.corkili.pa.validation.annotation.LongRange;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.LongRuleFactory;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.validator.ValidateResult;

public class LongValidator extends AbstractValidator<Long, LongConstraint> {

    private static LongValidator instance;
    
    static LongValidator getInstance() {
        if (instance == null) {
            synchronized (LongValidator.class) {
                if (instance == null) {
                    instance = new LongValidator();
                }
            }
        }
        return instance;
    }
    
    private LongValidator() {
    }
    
    @Override
    protected Class<?> getValidatorClass() {
        return LongValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Long> getValidateElementType() {
        return Long.class;
    }

    @Override
    public Class<LongConstraint> getAnnotationType() {
        return LongConstraint.class;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, long element,
                                       LongConstraint constraint, ValidateResult result) {
        Rule rule = LongRuleFactory.valueRangeRule(fieldName, constraint);
        long[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (long value : values) {
                if (element == value) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRange(String fieldName, long element,
                                  LongConstraint constraint, ValidateResult result) {
        Rule rule = LongRuleFactory.rangeRule(fieldName, constraint);
        boolean success = true;
        LongRange[] ranges = constraint.ranges();
        if (ranges.length != 0) {
            for (LongRange range : ranges) {
                if (!range.minInclude() && !range.maxInclude()) {
                    success = success && range.min() < element && element < range.max();
                } else if (range.minInclude() && !range.maxInclude()) {
                    success = success && range.min() <= element && element < range.max();
                } else if (!range.minInclude() && range.maxInclude()) {
                    success = success && range.min() < element && element <= range.max();
                } else {
                    success = success && range.min() <= element && element <= range.max();
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }
}
