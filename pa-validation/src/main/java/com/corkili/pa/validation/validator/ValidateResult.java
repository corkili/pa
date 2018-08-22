package com.corkili.pa.validation.validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.corkili.pa.common.dto.Pair;
import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.rule.Rule;

public final class ValidateResult {

    public static final String SUCCESS_MESSAGE_TEMPLATE = "\"{}\" validate success";

    private Map<Rule, Pair<Boolean, String>> resultMap;

    public ValidateResult() {
        resultMap = new HashMap<>();
    }

    public void put(Rule rule, Pair<Boolean, String> resultPair) {
        if (CheckUtils.allIsNotNull(rule, resultPair)) {
            resultMap.put(rule, resultPair);
        }
    }

    public Set<Rule> rules() {
        return resultMap.keySet();
    }

    public Collection<Pair<Boolean, String>> values() {
        return resultMap.values();
    }

    public static String getSuccessMessage(String fieldName) {
        return IUtils.format(SUCCESS_MESSAGE_TEMPLATE, fieldName);
    }

    public void mergeFrom(ValidateResult validateResult) {
        validateResult.resultMap.forEach((key, value) -> this.resultMap.put(key, value));
    }

    @Override
    public String toString() {
        return "ValidateResult{" +
                "resultMap=" + resultMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidateResult that = (ValidateResult) o;
        return Objects.equals(resultMap, that.resultMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultMap);
    }
}
