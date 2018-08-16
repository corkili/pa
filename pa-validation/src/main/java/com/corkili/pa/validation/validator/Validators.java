package com.corkili.pa.validation.validator;

import com.corkili.pa.common.dto.Pair;
import com.corkili.pa.common.dto.Result;
import com.corkili.pa.validation.rule.Rule;

public abstract class Validators {

    public static Result<ValidateResult> buildResult(String fieldName, ValidateResult validateResult) {
        boolean success = true;
        StringBuilder msgBuilder = new StringBuilder("[ ");
        for (Pair<Boolean, String> pair : validateResult.values()) {
            success = success && pair.getKey();
            if (!pair.getKey()) {
                msgBuilder.append(pair.getValue()).append(" ");
            }
        }
        if (success) {
            msgBuilder.delete(0, msgBuilder.length());
            msgBuilder.append("\"").append(fieldName).append("\" comply with all rules");
        } else {
            msgBuilder.append("]");
        }
        return new Result<>(success, msgBuilder.toString(), validateResult);
    }

    public static Pair<Boolean, String> getResultPair(boolean success, Rule rule, String fieldName) {
        return new Pair<>(success, success ? ValidateResult.getSuccessMessage(fieldName) : rule.getDescribe());
    }
}
