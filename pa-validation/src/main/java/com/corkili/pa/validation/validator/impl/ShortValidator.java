package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.buildResult;
import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.common.dto.Pair;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.validation.annotation.ShortConstraint;
import com.corkili.pa.validation.annotation.ShortRange;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.RuleFactory;
import com.corkili.pa.validation.rule.ShortRuleFactory;
import com.corkili.pa.validation.validator.AbstractValidator;
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
    public Result<ValidateResult> validate(String fieldName, Short element, ShortConstraint constraint) {
        ValidateResult validateResult = new ValidateResult();
        if (!validateShortNotNull(fieldName, element, constraint, validateResult) && isAssert()) {
            return buildResult(fieldName, validateResult);
        }
        if (element == null) {
            Rule nullRule = RuleFactory.FieldNullRule(String.class, fieldName);
            validateResult.put(nullRule, new Pair<>(true, nullRule.getDescribe()));
            return buildResult(fieldName, validateResult);
        }
        if (!validateShortValueRange(fieldName, element, constraint, validateResult)) {
            return buildResult(fieldName, validateResult);
        }
        if (!validateShortRange(fieldName, element, constraint, validateResult)) {
            return buildResult(fieldName, validateResult);
        }
        return buildResult(fieldName, validateResult);
    }

    @Override
    public Class<Short> getValidateElementType() {
        return Short.class;
    }

    @Override
    public Class<ShortConstraint> getAnnotationType() {
        return ShortConstraint.class;
    }

    private boolean validateShortNotNull(String fieldName, Short element,
                                         ShortConstraint constraint, ValidateResult result) {
        Rule rule = ShortRuleFactory.notNullRule(fieldName, constraint);
        boolean success = !constraint.notNull() || element != null;
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    private boolean validateShortValueRange(String fieldName, short element,
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

    private boolean validateShortRange(String fieldName, short element,
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
