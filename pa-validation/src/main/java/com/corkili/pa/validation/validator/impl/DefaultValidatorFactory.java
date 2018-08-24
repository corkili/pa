package com.corkili.pa.validation.validator.impl;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.corkili.pa.common.config.Config;
import com.corkili.pa.common.config.ConfigManager;
import com.corkili.pa.common.config.ConfigType;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.constant.ConfigConstants;
import com.corkili.pa.validation.exception.ValidationException;
import com.corkili.pa.validation.validator.Validator;
import com.corkili.pa.validation.validator.ValidatorFactory;

public class DefaultValidatorFactory implements ValidatorFactory {

    private static DefaultValidatorFactory instance;

    private Map<Class<?>, Validator<?, ?>> validators;

    private Set<Validator<?, ?>> validatorSet;

    private ConfigManager configManager;

    private Config config;

    private int updateConfInterval;

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            synchronized (DefaultValidatorFactory.class) {
                if (instance == null) {
                    instance = new DefaultValidatorFactory();
                }
            }
        }
        return instance;
    }

    private DefaultValidatorFactory() {
        validators = new HashMap<>();
        validatorSet = new HashSet<>();
        configManager = ConfigManager.getInstance();
        config = configManager.getConfig();
        loadValidator();
        loadConfig();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> Validator<E, ? extends Annotation> getValidatorByClass(Class<E> elementClazz) {
        try {
            if (validators.containsKey(elementClazz)) {
                return (Validator<E, ?>) validators.get(elementClazz);
            }
        } catch (Exception e) {
            throw new ValidationException(IUtils.format("no validator for {} ", elementClazz.getName()), e);
        }
        throw new ValidationException(IUtils.format("no validator for {} ", elementClazz.getName()));
    }

    private void loadValidator() {
        // TODO (corkili): load Validator
        validators.put(String.class, StringValidator.getInstance());
        validators.put(Short.class, ShortValidator.getInstance());
        validators.put(short.class, ShortValidator.getInstance());
        validators.put(Integer.class, IntegerValidator.getInstance());
        validators.put(int.class, IntegerValidator.getInstance());
        validators.put(Long.class, LongValidator.getInstance());
        validators.put(long.class, LongValidator.getInstance());
        validators.put(Float.class, FloatValidator.getInstance());
        validators.put(float.class, FloatValidator.getInstance());
        validators.put(Double.class, DoubleValidator.getInstance());
        validators.put(double.class, DoubleValidator.getInstance());
        validators.put(Map.class, StrObjMapValidator.getInstance());
        validatorSet.addAll(validators.values());
    }

    private void loadConfig() {
        configManager.registerConfigFile(System.getProperty(ConfigConstants.SYSTEM_PROPERTY_VALIDATOR_CONFIG_FILE,
                        ConfigConstants.DEFAULT__VALIDATOR_CONFIG_FILE), ConfigType.YAML_CONFIG);
        updateConfInterval = config.getInteger(ConfigConstants.KEY_VALIDATOR_ASSERT_UPDATE_INTERVAL_SEC,
                ConfigConstants.DEFAULT_VALIDATOR_ASSERT_UPDATE_INTERVAL_SEC);
    }

    private void updateConfig() {
        final boolean defaultAssert = config.getBoolean(ConfigConstants.KEY_VALIDATOR_ASSERT_ENABLE,
                ConfigConstants.DEFAULT_VALIDATOR_ASSERT_ENABLE);
        validatorSet.forEach(validator -> {
            Class<?> type = validator.getClass();
            String key = ConfigConstants.PREFIX + type.getName() + ConfigConstants.SUFFIX_ASSERT_ENABLE;

        });
    }
}
