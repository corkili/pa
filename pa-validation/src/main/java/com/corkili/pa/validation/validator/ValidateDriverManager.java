package com.corkili.pa.validation.validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.corkili.pa.common.util.CheckUtils;

public class ValidateDriverManager {

    private static ValidateDriverManager instance;

    private Map<String, ValidateDriver> drivers;

    public static ValidateDriverManager getInstance() {
        if (instance == null) {
            synchronized (ValidateDriverManager.class) {
                if (instance == null) {
                    instance = new ValidateDriverManager();
                }
            }
        }
        return instance;
    }

    private ValidateDriverManager() {
        drivers = new ConcurrentHashMap<>();
    }

    public void registry(String driverName, ValidateDriver validateDriver) {
        if (CheckUtils.allIsNotNull(driverName, validateDriver)) {
            drivers.put(driverName, validateDriver);
        }
    }

    public ValidateDriver getDriverByName(String driverName) {
        return drivers.get(driverName);
    }

}
