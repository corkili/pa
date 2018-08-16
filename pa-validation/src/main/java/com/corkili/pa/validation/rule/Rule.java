package com.corkili.pa.validation.rule;

import java.util.Objects;

public final class Rule {

    public static final Rule EMPTY_RULE = new Rule(null, null, null);

    private final Class<?> type;

    private final String fieldName;

    private final String describe;

    private final int _hashcode;

    public Rule(Class<?> type, String fieldName, String describe) {
        this.type = type;
        this.fieldName = fieldName;
        this.describe = describe;
        this._hashcode = Objects.hash(type, fieldName, describe);
    }

    public Class<?> getType() {
        return type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDescribe() {
        return describe;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "type=" + type +
                ", fieldName='" + fieldName + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(type, rule.type) &&
                Objects.equals(fieldName, rule.fieldName) &&
                Objects.equals(describe, rule.describe);
    }

    @Override
    public int hashCode() {
        return this._hashcode;
    }
}
