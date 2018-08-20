package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.buildResult;
import static com.corkili.pa.validation.validator.Validators.getResultPair;

import com.corkili.pa.common.dto.Pair;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.rule.IntRuleFactory;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.RuleFactory;
import com.corkili.pa.validation.validator.AbstractValidator;
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
    public Result<ValidateResult> validate(String fieldName, Integer element, IntConstraint constraint) {
        ValidateResult validateResult = new ValidateResult();
        if (!validateIntNotNull(fieldName, element, constraint, validateResult) && isAssert()) {
            return buildResult(fieldName, validateResult);
        }
        if (element == null) {
            Rule nullRule = RuleFactory.FieldNullRule(String.class, fieldName);
            validateResult.put(nullRule, new Pair<>(true, nullRule.getDescribe()));
            return buildResult(fieldName, validateResult);
        }
        if (!validateIntValueRange(fieldName, element, constraint, validateResult)) {
            return buildResult(fieldName, validateResult);
        }
        if (!validateIntRange(fieldName, element, constraint, validateResult)) {
            return buildResult(fieldName, validateResult);
        }
        return buildResult(fieldName, validateResult);
    }

    @Override
    public Class<Integer> getValidateElementType() {
        return Integer.class;
    }

    @Override
    public Class<IntConstraint> getAnnotationType() {
        return IntConstraint.class;
    }

    private boolean validateIntNotNull(String fieldName, Integer element,
                                    IntConstraint constraint, ValidateResult result) {
        Rule rule = IntRuleFactory.notNullRule(fieldName, constraint);
        boolean success = !constraint.notNull() || element != null;
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    private boolean validateIntValueRange(String fieldName, int element,
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

    private boolean validateIntRange(String fieldName, int element,
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
