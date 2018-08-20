package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import com.corkili.pa.validation.annotation.IntRange;
import com.corkili.pa.validation.annotation.StringConstraint;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.StringRuleFactory;
import com.corkili.pa.validation.validator.ValidateResult;

public class StringValidator extends AbstractValidator<String, StringConstraint> {

    private static StringValidator instance;

    private Map<String, Pattern> regexMap;

    static StringValidator getInstance() {
        if (instance == null) {
            synchronized (StringValidator.class) {
                if (instance == null) {
                    instance = new StringValidator();
                }
            }
        }
        return instance;
    }

    private StringValidator() {
        regexMap = new ConcurrentHashMap<>();
    }

    @Override
    protected Class<?> getValidatorClass() {
        return StringValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<String> getValidateElementType() {
        return String.class;
    }

    @Override
    public Class<StringConstraint> getAnnotationType() {
        return StringConstraint.class;
    }

    @ValidateMethod
    private boolean validateNotEmpty(String fieldName, String element,
                                     StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.notEmptyRule(fieldName, constraint);
        boolean success = !constraint.notEmpty() || !element.isEmpty();
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateLength(String fieldName, String element,
                                   StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.lengthRule(fieldName, constraint);
        int length = constraint.length();
        boolean success = length < 0 || length == element.length();
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateLengthRange(String fieldName, String element,
                                        StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.lengthRangeRule(fieldName, constraint);
        boolean success = true;
        IntRange[] ranges = constraint.lengthRanges();
        if (ranges.length != 0) {
            int length = element.length();
            for (IntRange range : ranges) {
                success = success && range.min() <= length && length <= range.max();
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateValueRange(String fieldName, String element,
                                       StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.valueRangeRule(fieldName, constraint);
        String[] values = constraint.valueRange();
        boolean success = false;
        if (values.length == 0) {
            success = true;
        } else {
            for (String value : values) {
                if (element.equals(value)) {
                    success = true;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateContains(String fieldName, String element,
                                       StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.containsRule(fieldName, constraint);
        String[] contains = constraint.contains();
        boolean success = false;
        if (contains.length == 0) {
            success = true;
        } else {
            for (String value : contains) {
                if (element.contains(value)) {
                    success = true;
                    break;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateNotContains(String fieldName, String element,
                                     StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.notContainsRule(fieldName, constraint);
        String[] notContains = constraint.notContains();
        boolean success = true;
        if (notContains.length != 0) {
            for (String value : notContains) {
                if (element.contains(value)) {
                    success = false;
                    break;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateStartWith(String fieldName, String element,
                                        StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.startWithRule(fieldName, constraint);
        String[] start = constraint.startWith();
        boolean success = false;
        if (start.length == 0) {
            success = true;
        } else {
            for (String value : start) {
                if (element.startsWith(value)) {
                    success = true;
                    break;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateEndWith(String fieldName, String element,
                                    StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.endWithRule(fieldName, constraint);
        String[] end = constraint.endWith();
        boolean success = false;
        if (end.length == 0) {
            success = true;
        } else {
            for (String value : end) {
                if (element.endsWith(value)) {
                    success = true;
                    break;
                }
            }
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateCase(String fieldName, String element,
                                    StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.caseRule(fieldName, constraint);
        boolean ignore = constraint.ignoreCase();
        boolean lower = constraint.lower();
        boolean upper = constraint.upper();
        boolean success;
        if (ignore) {
            success = true;
        } else if (lower && !upper) {
            success = element.toLowerCase().equals(element);
        } else if (upper && !lower) {
            success = element.toUpperCase().equals(element);
        } else {
            success = true;
        }
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    @ValidateMethod
    private boolean validateRegex(String fieldName, String element,
                                 StringConstraint constraint, ValidateResult result) {
        Rule rule = StringRuleFactory.regex(fieldName, constraint);
        Pattern pattern = getPattern(constraint.regex());
        boolean success = pattern.matcher(element).matches();
        result.put(rule, getResultPair(success, rule, fieldName));
        return success;
    }

    private Pattern getPattern(String regex) {
        if (!regexMap.containsKey(regex)) {
            regexMap.put(regex, Pattern.compile(regex));
        }
        return regexMap.get(regex);
    }

}
