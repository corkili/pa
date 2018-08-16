package com.corkili.pa.validation.validator;

import java.lang.annotation.Annotation;

public interface ValidatorFactory {

    <E, A extends Annotation> Validator<E, A> getValidatorByClass(Class<E> elementClazz, Class<A> constraintClazz);

}
