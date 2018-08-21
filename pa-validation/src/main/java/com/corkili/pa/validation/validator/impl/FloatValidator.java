package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;
import static com.corkili.pa.validation.util.FloatUtil.*;

import com.corkili.pa.validation.annotation.FloatConstraint;
import com.corkili.pa.validation.annotation.FloatRange;
import com.corkili.pa.validation.annotation.FloatValue;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.FloatRuleFactory;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.util.FloatUtil;
import com.corkili.pa.validation.validator.ValidateResult;

public class FloatValidator extends AbstractValidator<Float, FloatConstraint> {

    private static FloatValidator instance;

    static FloatValidator getInstance() {
        if (instance == null) {
            synchronized (FloatValidator.class) {
                if (instance == null) {
                    instance = new FloatValidator();
                }
            }
        }
        return instance;
    }

    private FloatValidator() {
    }

    @Override
    protected Class<?> getValidatorClass() {
        return FloatValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Float> getValidateElementType() {
        return Float.class;
    }

    @Override
    public Class<FloatConstraint> getAnnotationType() {
        return FloatConstraint.class;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, float element,
                                       FloatConstraint constraint, ValidateResult result) {
        Rule rule = FloatRuleFactory.valueRangeRule(fieldName, constraint);
        FloatValue[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (FloatValue value : values) {
                if (FloatUtil.equals(element, value.value(), value.precision())) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRange(String fieldName, float element,
                                  FloatConstraint constraint, ValidateResult result) {
        Rule rule = FloatRuleFactory.rangeRule(fieldName, constraint);
        boolean success = true;
        FloatRange[] ranges = constraint.ranges();
        if (ranges.length != 0) {
            for (FloatRange range : ranges) {
                FloatValue min = range.min();
                FloatValue max = range.max();
                if (range.minInclude() && range.maxInclude()) {
                    success = success && lessOrEquals(min.value(), element, min.precision())
                            && lessOrEquals(element, max.value(), max.precision());
                } else if (range.minInclude() && !range.maxInclude()) {
                    success = success && lessOrEquals(min.value(), element, min.precision())
                            && less(element, max.value(), max.precision());
                } else if (!range.minInclude() && range.maxInclude()) {
                    success = success && less(min.value(), element, min.precision())
                            && lessOrEquals(element, max.value(), max.precision());
                } else {
                    success = success && less(min.value(), element, min.precision())
                            && less(element, max.value(), max.precision());
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

}
