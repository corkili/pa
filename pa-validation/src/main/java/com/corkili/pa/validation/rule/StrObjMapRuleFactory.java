package com.corkili.pa.validation.rule;

import com.corkili.pa.common.util.CheckUtils;
import com.corkili.pa.common.util.IUtils;
import com.corkili.pa.validation.annotation.StrObjMapValue;

public abstract class StrObjMapRuleFactory {

    public static Rule requiredRule(String fieldName, StrObjMapValue value) {
        if (CheckUtils.hasNull(fieldName, value)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        if (value.required()) {
            describe = IUtils.format("\"{}\".\"{}\" is required", fieldName, value.key());
        } else {
            describe = IUtils.format("\"{}\".\"{}\" is optional", fieldName, value.key());
        }
        return new Rule(Object.class, fieldName, describe);
    }
    
    public static Rule valueTypeRule(String fieldName, StrObjMapValue value) {
        if (CheckUtils.hasNull(fieldName, value)) {
            return Rule.EMPTY_RULE;
        }
        String describe;
        String type = value.valueType().getName();
        if (value.required()) {
            describe = IUtils.format("type of \"{}\".\"{}\" should be \"{}\"", fieldName, value.key(), type);
        } else {
            describe = IUtils.format("type of \"{}\".\"{}\" should be \"{}\"", fieldName, value.key(), type);
        }
        return new Rule(Object.class, fieldName, describe);
    }

    public static Rule valueRule(String fieldName, StrObjMapValue value) {
        if (CheckUtils.hasNull(fieldName, value)) {
            return Rule.EMPTY_RULE;
        }
        String describe = IUtils.format("value of \"{}\".\"{}\" ", fieldName, value.key());
        int cnt = 0;
        if (value.validateString()) {
            describe += "has constraint of string";
            cnt++;
        }
        if (value.validateShort()) {
            if (cnt == 0) {
                describe += "has constraint of short";
            }
            cnt++;
        }
        if (value.validateInt()) {
            if (cnt == 0) {
                describe += "has constraint of int";
            }
            cnt++;
        }
        if (value.validateLong()) {
            if (cnt == 0) {
                describe += "has constraint of long";
            }
            cnt++;
        }
        if (value.validateFloat()) {
            if (cnt == 0) {
                describe += "has constraint of float";
            }
            cnt++;
        }
        if (value.validateDouble()) {
            if (cnt == 0) {
                describe += "has constraint of double";
            }
            cnt++;
        }
        if (cnt == 0){
            describe += "is not other constraint";
        } else if (cnt > 1){
            describe += "has more than one other constraint";
        }
        return new Rule(Object.class, fieldName, describe);
    }


}
