package com.corkili.pa.validation.validator;

import com.corkili.pa.common.dto.Result;

public interface Validator<E> {

    Result<ValidateResult> validate(E element);

    Class<E> getValidateElementType();

}
