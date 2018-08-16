package com.corkili.pa.validation.validator;

import java.lang.annotation.Annotation;

import com.corkili.pa.common.dto.Result;

public interface Validator<E, A extends Annotation> {

    Result<ValidateResult> validate(E element, A annotation);

    Class<E> getValidateElementType();

    Class<A> getAnnotationType();

}
