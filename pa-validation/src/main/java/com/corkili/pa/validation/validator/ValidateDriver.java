package com.corkili.pa.validation.validator;

import java.util.List;

import com.corkili.pa.common.dto.Result;

public interface ValidateDriver {

    <T> Result<List<ValidatorResult>> validate(T obj, Class<T> clazz);

}
