package com.corkili.pa.validation.validator.impl;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.exception.ValidationException;
import com.corkili.pa.validation.validator.Validator;
import com.corkili.pa.validation.validator.ValidatorFactory;

public class DefaultValidatorFactory implements ValidatorFactory {

    private static DefaultValidatorFactory instance;

    private Map<Class<?>, Validator<?, ?>> validators;

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            synchronized (DefaultValidatorFactory.class) {
                if (instance == null) {
                    instance = new DefaultValidatorFactory();
                }
            }
        }
        return instance;
    }

    private DefaultValidatorFactory() {
        validators = new HashMap<>();
        loadValidator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, A extends Annotation> Validator<E, A> getValidatorByClass(Class<E> elementClazz, Class<A> constraintClazz) {
        try {
            if (validators.containsKey(elementClazz)) {
                return (Validator<E, A>) validators.get(elementClazz);
            }
        } catch (Exception e) {
            throw new ValidationException(IUtils.format("no validator for {} ", elementClazz.getName()), e);
        }
        throw new ValidationException(IUtils.format("no validator for {} ", elementClazz.getName()));
    }

    private void loadValidator() {
        validators.put(String.class, StringValidator.getInstance());
    }
}
