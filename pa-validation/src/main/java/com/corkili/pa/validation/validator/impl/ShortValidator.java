package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.validation.annotation.ShortConstraint;
import com.corkili.pa.validation.annotation.ShortRange;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.ShortRuleFactory;
import com.corkili.pa.validation.validator.ValidateResult;

public class ShortValidator extends AbstractValidator<Short, ShortConstraint> {

    private static ShortValidator instance;

    static ShortValidator getInstance() {
        if (instance == null) {
            synchronized (ShortValidator.class) {
                if (instance == null) {
                    instance = new ShortValidator();
                }
            }
        }
        return instance;
    }

    private ShortValidator() {
    }

    @Override
    protected Class<?> getValidatorClass() {
        return ShortValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Short> getValidateElementType() {
        return Short.class;
    }

    @Override
    public Class<ShortConstraint> getAnnotationType() {
        return ShortConstraint.class;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, short element,
                                            ShortConstraint constraint, ValidateResult result) {
        Rule rule = ShortRuleFactory.valueRangeRule(fieldName, constraint);
        short[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (short value : values) {
                if (element == value) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRange(String fieldName, short element,
                                       ShortConstraint constraint, ValidateResult result) {
        Rule rule = ShortRuleFactory.rangeRule(fieldName, constraint);
        boolean success = true;
        ShortRange[] ranges = constraint.ranges();
        if (ranges.length != 0) {
            for (ShortRange range : ranges) {
                success = success && range.min() <= element && element <= range.max();
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }
    
}
