package com.corkili.pa.validation.validator.impl;

import static com.corkili.pa.validation.validator.Validators.getResultPair;

import java.util.Map;

import com.corkili.pa.common.dto.Result;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.StrObjMapConstraint;
import com.corkili.pa.validation.annotation.StrObjMapValue;
import com.corkili.pa.validation.annotation.ValidateMethod;
import com.corkili.pa.validation.rule.Rule;
import com.corkili.pa.validation.rule.StrObjMapRuleFactory;
import com.corkili.pa.validation.validator.ValidateResult;

public class StrObjMapValidator extends AbstractValidator<Map, StrObjMapConstraint> {

    private static StrObjMapValidator instance;

    static StrObjMapValidator getInstance() {
        if (instance == null) {
            synchronized (StrObjMapValidator.class) {
                if (instance == null) {
                    instance = new StrObjMapValidator();
                }
            }
        }
        return instance;
    }

    private StrObjMapValidator() {
    }


    @Override
    protected Class<?> getValidatorClass() {
        return StrObjMapValidator.class;
    }

    @Override
    protected AbstractValidator getValidator() {
        return this;
    }

    @Override
    public Class<Map> getValidateElementType() {
        return Map.class;
    }

    @Override
    public Class<StrObjMapConstraint> getAnnotationType() {
        return StrObjMapConstraint.class;
    }

    @Override
    protected boolean preprocess(String fieldName, Map element, StrObjMapConstraint constraint, ValidateResult result) {
        boolean success = true;
        if (element != null) {
            for (Object key : element.keySet()) {
                if (!(key instanceof String)) {
                    success = false;
                    break;
                }
            }
        }
        if (!success) {
            Rule rule = StrObjMapRuleFactory.mapKeyTypeRUle(fieldName);
            result.put(rule, getResultPair(false, rule, fieldName));
        }
        return success;
    }

    @ValidateMethod
    private boolean validateAll(String fieldName, Map<String, Object> element,
                                     StrObjMapConstraint constraint, ValidateResult result) {
        StrObjMapValue[] values = constraint.value();
        boolean success = true;
        for (StrObjMapValue value : values) {
            if (validateRequired(fieldName, element, value, result)) {
                if (value.required()) {
                    if (validateValueType(fieldName, element, value, result)) {
                        success = success && validateValue(fieldName, element, value, result);
                    } else {
                        success = false;
                    }
                }
            } else {
                success = false;
            }
        }
        return success;
    }

    private boolean validateRequired(String fieldName, Map<String, Object> element,
                                     StrObjMapValue value, ValidateResult result) {
        Rule rule = StrObjMapRuleFactory.requiredRule(fieldName, value);
        boolean success = !value.required() || element.containsKey(value.key());
        result.put(rule, getResultPair(success, rule, generateCompleteFieldName(fieldName, value.key())));
        return success;
    }

    private boolean validateValueType(String fieldName, Map<String, Object> element,
                                      StrObjMapValue value, ValidateResult result) {
        Rule rule = StrObjMapRuleFactory.valueTypeRule(fieldName, value);
        boolean success = value.valueType().isInstance(element.get(value.key()));
        result.put(rule, getResultPair(success, rule, generateCompleteFieldName(fieldName, value.key())));
        return success;
    }

    private boolean validateValue(String fieldName, Map<String, Object> element,
                                      StrObjMapValue value, ValidateResult result) {
        Rule rule = StrObjMapRuleFactory.valueRule(fieldName, value);
        ValidateResult tmpResult = new ValidateResult();
        boolean success = true;
        Class<?> type = value.valueType();
        String key = value.key();
        Object obj = element.get(key);
        int cnt = 0;
        if (value.validateString() && type.equals(String.class) && type.isInstance(obj)) {
            Result<ValidateResult> res = StringValidator.getInstance()
                    .validate(generateCompleteFieldName(fieldName, key), (String) obj, value.stringConstraint());
            success = res.isSuccess();
            tmpResult.mergeFrom(res.getExtra());
            cnt++;
        }
        if (value.validateShort() && (type.equals(Short.class) || type.equals(short.class)) && type.isInstance(obj)) {
            if (cnt == 0) {
                Result<ValidateResult> res;
                if (obj instanceof Short) {
                    res = ShortValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (Short) obj, value.shortConstraint());
                } else {
                    res = ShortValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (short) obj, value.shortConstraint());
                }
                success = res.isSuccess();
                tmpResult.mergeFrom(res.getExtra());
            }
            cnt++;
        }
        if (value.validateInt() && (type.equals(Integer.class) || type.equals(int.class)) && type.isInstance(obj)) {
            if (cnt == 0) {
                Result<ValidateResult> res;
                if (obj instanceof Integer) {
                    res = IntegerValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (Integer) obj, value.intConstraint());
                } else {
                    res = IntegerValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (int) obj, value.intConstraint());
                }
                success = res.isSuccess();
                tmpResult.mergeFrom(res.getExtra());
            }
            cnt++;
        }
        if (value.validateLong() && (type.equals(Long.class) || type.equals(long.class)) && type.isInstance(obj)) {
            if (cnt == 0) {
                Result<ValidateResult> res;
                if (obj instanceof Long) {
                    res = LongValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (Long) obj, value.longConstraint());
                } else {
                    res = LongValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (long) obj, value.longConstraint());
                }
                success = res.isSuccess();
                tmpResult.mergeFrom(res.getExtra());
            }
            cnt++;
        }
        if (value.validateFloat() && (type.equals(Float.class) || type.equals(float.class)) && type.isInstance(obj)) {
            if (cnt == 0) {
                Result<ValidateResult> res;
                if (obj instanceof Float) {
                    res = FloatValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (Float) obj, value.floatConstraint());
                } else {
                    res = FloatValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (float) obj, value.floatConstraint());
                }
                success = res.isSuccess();
                tmpResult.mergeFrom(res.getExtra());
            }
            cnt++;
        }
        if (value.validateDouble() && (type.equals(Double.class) || type.equals(double.class)) && type.isInstance(obj)) {
            if (cnt == 0) {
                Result<ValidateResult> res;
                if (obj instanceof Double) {
                    res = DoubleValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (Double) obj, value.doubleConstraint());
                } else {
                    res = DoubleValidator.getInstance()
                            .validate(generateCompleteFieldName(fieldName, key), (double) obj, value.doubleConstraint());
                }
                success = res.isSuccess();
                tmpResult.mergeFrom(res.getExtra());
            }
            cnt++;
        }
        if (cnt == 1) {
            result.mergeFrom(tmpResult);
        } else if (cnt > 1) {
            success = false;
        }
        result.put(rule, getResultPair(success, rule, generateCompleteFieldName(fieldName, value.key())));
        return success;
    }

    private String generateCompleteFieldName(String fieldName, String key) {
        return IUtils.format("\"{}\".\"{}\"", fieldName, key);
    }
}
