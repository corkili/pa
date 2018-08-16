package com.corkili.pa.validation.validator.def;

import java.lang.annotation.Annotation;
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
import com.corkili.pa.validation.validator.ValidateResult;

public class ValidateExecutor<E> {

    private Class<E> objectType;

    private Map<Field, Validator<?, ?>> validatorMap;

    public ValidateExecutor(Class<E> objectType) {
        this.objectType = objectType;
        validatorMap = new HashMap<>();
    }

    public Result<List<ValidateResult>> validate(Object object) {
        List<ValidateResult> validateResults = new ArrayList<>(validatorMap.size());
        boolean success = true;
        StringBuilder msgBuilder = new StringBuilder("[");
        for (Entry<Field, Validator<?, ?>> entry : validatorMap.entrySet()) {
            Result<ValidateResult> result = validate(object, entry.getKey(), entry.getValue());
            success = success && result.isSuccess();
            if (!result.isSuccess()) {
                msgBuilder.append(result.getMessage());
            }
            validateResults.add(result.getExtra());
        }
        if (success) {
            msgBuilder.delete(0, msgBuilder.length());
            msgBuilder.append("all field validated OK");
        } else {
            msgBuilder.append("]");
        }
        return new Result<>(success, msgBuilder.toString(), validateResults);
    }

    public <T, A extends Annotation> boolean add(Field field, Validator<T, A> validator) {
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

    private  <T, A extends Annotation> Result<ValidateResult> validate(Object object, Field field, Validator<T, A> validator) {
        try {
            Object element = field.get(object);
            if (validator.getValidateElementType().isInstance(field.get(object)) &&
                    CheckUtils.isNotNull(field.getAnnotation(validator.getAnnotationType()))) {
                return validator.validate(validator.getValidateElementType().cast(element),
                        field.getAnnotation(validator.getAnnotationType()));
            } else {
                return new Result<>(false,
                        IUtils.format("validate failed: field's type is invalid"),
                        new ValidateResult());
            }
        } catch (Exception e) {
            return new Result<>(false,
                    IUtils.format("validate exception occur: {}", IUtils.stringifyError(e)),
                    new ValidateResult());
        }
    }


}
