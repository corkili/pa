package com.corkili.pa.validation.validator.def;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.validator.Validator;
import com.corkili.pa.validation.validator.ValidatorResult;

public class ValidateExecutor<E> {

    private Class<E> objectType;

    private Map<Field, Validator<?>> validatorMap;

    public ValidateExecutor(Class<E> objectType) {
        this.objectType = objectType;
        validatorMap = new HashMap<>();
    }

    public Result<List<ValidatorResult>> validate(Object object) {
        List<ValidatorResult> validatorResults = new ArrayList<>(validatorMap.size());
        boolean success = true;
        StringBuilder msgBuilder = new StringBuilder("[");
        for (Entry<Field, Validator<?>> entry : validatorMap.entrySet()) {
            Result<ValidatorResult> result = validate(object, entry.getKey(), entry.getValue());
            success = success && result.isSuccess();
            if (!result.isSuccess()) {
                msgBuilder.append(result.getMessage());
            }
            validatorResults.add(result.getExtra());
        }
        if (success) {
            msgBuilder.delete(0, msgBuilder.length());
            msgBuilder.append("all field validated OK");
        } else {
            msgBuilder.append("]");
        }
        return new Result<>(success, msgBuilder.toString(), validatorResults);
    }

    public <T> boolean add(Field field, Validator<T> validator) {
        if (CheckUtils.hasNull(field, validator)) {
            return false;
        } else {
            validatorMap.put(field, validator);
            field.setAccessible(true);
            return true;
        }
    }

    public void remove(Field field) {
        if (CheckUtils.isNotNull(field)) {
            validatorMap.remove(field);
        }
    }

    private  <T> Result<ValidatorResult> validate(Object object, Field field, Validator<T> validator) {
        try {
            Object element = field.get(object);
            if (validator.getValidateElementType().isInstance(field.get(object))) {
                return validator.validate(validator.getValidateElementType().cast(element));
            } else {
                return new Result<>(false,
                        IUtils.format("validate failed: field's type is invalid"),
                        new ValidatorResult());
            }
        } catch (Exception e) {
            return new Result<>(false,
                    IUtils.format("validate exception occur: {}", IUtils.stringifyError(e)),
                    new ValidatorResult());
        }
    }


}
