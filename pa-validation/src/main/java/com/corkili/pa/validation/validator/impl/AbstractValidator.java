package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.buildResult;
import static com.corkili.pa.validation.validator.Validators.getResultPair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.corkili.pa.common.dto.Pair;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.exception.ValidationException;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.RuleFactory;
import com.corkili.pa.validation.validator.ValidateResult;
import com.corkili.pa.validation.validator.Validator;

public abstract class AbstractValidator<E, A extends Annotation> implements Validator<E, A> {

    private volatile boolean assertModel;

    public AbstractValidator() {
        this.assertModel = false;
    }

    public void setAssertModel(boolean assertModel) {
        this.assertModel = assertModel;
    }

    public boolean isAssert() {
        return this.assertModel;
    }

    public Result<ValidateResult> validate(String fieldName, E element, A constraint) {
        ValidateResult validateResult = new ValidateResult();
        if (!preprocess(fieldName, element, constraint, validateResult)) {
            return buildResult(fieldName, validateResult);
        }
        if (!validateNotNull(fieldName, element, constraint, validateResult) && assertModel) {
            return buildResult(fieldName, validateResult);
        }
        if (element == null) {
            Rule nullRule = RuleFactory.FieldNullRule(String.class, fieldName);
            validateResult.put(nullRule, new Pair<>(true, nullRule.getDescribe()));
            return buildResult(fieldName, validateResult);
        }
        Method[] methods = getValidatorClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ValidateMethod.class)) {
                if (!invokeValidateMethod(method, fieldName, element, constraint, validateResult)) {
                    return buildResult(fieldName, validateResult);
                }
            }
        }
        return buildResult(fieldName, validateResult);
    }

    protected abstract Class<?> getValidatorClass();

    protected abstract AbstractValidator getValidator();

    protected boolean preprocess(String fieldName, E element, A constraint, ValidateResult result) {
        return true;
    }

    private boolean validateNotNull(String fieldName, E element, A constraint, ValidateResult result) {
        Rule rule = RuleFactory.notNullRule(fieldName, constraint, getValidateElementType());
        try {
            boolean success = !(boolean)constraint.getClass().getMethod("notNull").invoke(constraint) || element != null;
            result.put(rule, getResultPair(success, rule, fieldName));
            return success;
        } catch (Exception e) {
            throw new ValidationException(
                    IUtils.format("class \"{}\" not contains method \"boolean notNull();\"",
                            constraint.getClass().getName()), e);
        }
    }

    private boolean invokeValidateMethod(Method method, String fieldName, E element,
                                         A constraint, ValidateResult validateResult) {
        boolean isAccessible = method.isAccessible();
        try {
            method.setAccessible(true);
            return (boolean) method.invoke(getValidator(), fieldName, element, constraint, validateResult)
                    || !assertModel;
        } catch (Exception e) {
            throw new ValidationException(IUtils.format("parameters of method \"{}\" is not " +
                            "(String, ElementType, ConstraintAnnotation, ValidateResult)", method.getName()), e);
        } finally {
            method.setAccessible(isAccessible);
        }
    }
}
