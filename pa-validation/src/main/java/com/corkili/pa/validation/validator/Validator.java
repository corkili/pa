package com.corkili.pa.validation.validator;

import java.lang.annotation.Annotation;

import com.corkili.pa.common.dto.Result;

public interface Validator<E, A extends Annotation> {

    Result<ValidateResult> validate(String fieldName, E element, A constraint);

    Class<E> getValidateElementType();

    Class<A> getAnnotationType();

    void setAssert(boolean isAssert);
    
    boolean isAssert();

}
