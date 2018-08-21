package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.util.DoubleUtil.less;
import static com.corkili.pa.validation.util.DoubleUtil.lessOrEquals;
import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.validation.annotation.DoubleConstraint;
import com.corkili.pa.validation.annotation.DoubleRange;
import com.corkili.pa.validation.annotation.DoubleValue;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.DoubleRuleFactory;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.util.DoubleUtil;
import com.corkili.pa.validation.validator.ValidateResult;

public class DoubleValidator extends AbstractValidator<Double, DoubleConstraint> {

    private static DoubleValidator instance;

    static DoubleValidator getInstance() {
        if (instance == null) {
            synchronized (DoubleValidator.class) {
                if (instance == null) {
                    instance = new DoubleValidator();
                }
            }
        }
        return instance;
    }

    private DoubleValidator() {
    }
    
    @Override
    protected Class<?> getValidatorClass() {
        return DoubleValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Double> getValidateElementType() {
        return Double.class;
    }

    @Override
    public Class<DoubleConstraint> getAnnotationType() {
        return DoubleConstraint.class;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, double element,
                                       DoubleConstraint constraint, ValidateResult result) {
        Rule rule = DoubleRuleFactory.valueRangeRule(fieldName, constraint);
        DoubleValue[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (DoubleValue value : values) {
                if (DoubleUtil.equals(element, value.value(), value.precision())) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRange(String fieldName, double element,
                                  DoubleConstraint constraint, ValidateResult result) {
        Rule rule = DoubleRuleFactory.rangeRule(fieldName, constraint);
        boolean success = true;
        DoubleRange[] ranges = constraint.ranges();
        if (ranges.length != 0) {
            for (DoubleRange range : ranges) {
                DoubleValue min = range.min();
                DoubleValue max = range.max();
                if (range.minInclude() && range.maxInclude()) {
                    success = success && lessOrEquals(min.value(), element, min.precision())
                            && lessOrEquals(element, max.value(), max.precision());
                } else if (!range.minInclude() && range.maxInclude()) {
                    success = success && less(min.value(), element, min.precision())
                            && lessOrEquals(element, max.value(), max.precision());
                } else if (!range.minInclude() && !range.maxInclude()) {
                    success = success && less(min.value(), element, min.precision())
                            && less(element, max.value(), max.precision());
                } else {
                    success = success && lessOrEquals(min.value(), element, min.precision())
                            && less(element, max.value(), max.precision());
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }
}
