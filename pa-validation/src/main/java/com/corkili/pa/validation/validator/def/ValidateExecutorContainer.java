package com.corkili.pa.validation.validator.def;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValidateExecutorContainer {

    private static ValidateExecutorContainer instance;

    private Map<Class<?>, ValidateExecutor<?>> executorMap;

    public static ValidateExecutorContainer getInstance() {
        if (instance == null) {
            synchronized (ValidateExecutorContainer.class) {
                if (instance == null) {
                    instance = new ValidateExecutorContainer();
                }
            }
        }
        return instance;
    }

    private ValidateExecutorContainer() {
        executorMap = new ConcurrentHashMap<>();
    }

    public boolean containExecutorFor(Class<?> clazz) {
        return executorMap.containsKey(clazz);
    }

    public ValidateExecutor<?> getExecutorFor(Class<?> clazz) {
        return executorMap.get(clazz);
    }

}
