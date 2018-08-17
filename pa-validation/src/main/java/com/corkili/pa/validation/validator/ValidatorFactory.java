package com.corkili.pa.validation.validator;

import java.lang.annotation.Annotation;

public interface ValidatorFactory {

    <E> Validator<E, ? extends Annotation> getValidatorByClass(Class<E> elementClazz);

}
