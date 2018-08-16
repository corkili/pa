package com.corkili.pa.validation.validator.def;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.Validated;
import com.corkili.pa.validation.validator.ValidateDriver;
import com.corkili.pa.validation.validator.ValidateDriverManager;
import com.corkili.pa.validation.validator.ValidateResult;

public class DefaultValidateDriver implements ValidateDriver {

    private ValidateExecutorContainer executorContainer;

    static {
        ValidateDriverManager.getInstance().registry(
                DefaultValidateDriver.class.getName(),
                new DefaultValidateDriver());
    }

    private DefaultValidateDriver() {
        executorContainer = ValidateExecutorContainer.getInstance();
    }

    @Override
    public <T> Result<List<ValidateResult>> validate(T obj, Class<T> clazz) {
        if (isValidated(clazz)) {
            return new Result<>(false,
                    IUtils.format("class {} can't be validated without annotation {}",
                            clazz.getName(), Validated.class.getName()),
                    new ArrayList<>());
        }
        if (!executorContainer.containExecutorFor(clazz) && !initExecutor(clazz)) {
            return new Result<>(false,
                    IUtils.format("init validateExecutor for {} failed", clazz.getName()),
                    new ArrayList<>());
        }
        return executorContainer.getExecutorFor(clazz).validate(obj);
    }

    private boolean isValidated(Class<?> clazz) {
        return clazz.getAnnotation(Validated.class) != null;
    }

    private <T> boolean initExecutor(Class<T> clazz) {
        ValidateExecutor<T> validateExecutor = new ValidateExecutor<>(clazz);
        boolean success = true;
        Field[] fields = clazz.getDeclaredFields();

        return success;
    }

}