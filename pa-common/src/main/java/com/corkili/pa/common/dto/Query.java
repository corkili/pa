package com.corkili.pa.common.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.corkili.pa.common.util.CheckUtils;

public final class Query {

    private final Map<Object, Object> params;

    public Query() {
        params = new HashMap<>();
    }

    public Query(Query query) {
        this.params = new HashMap<>();
        if (CheckUtils.isNotNull(query)) {
            this.params.putAll(query.params);
        }
    }

    public boolean add(Object name, Object param) {
        if (CheckUtils.hasNull(name, param)) {
            return false;
        }
        params.put(name, param);
        return true;
    }

    public boolean add(Pair<?, ?> nameAndParam) {
        if (CheckUtils.isNull(nameAndParam) ||
                CheckUtils.isNull(nameAndParam.getKey()) ||
                CheckUtils.isNull(nameAndParam.getValue())) {
            return false;
        }
        params.put(nameAndParam.getKey(), nameAndParam.getValue());
        return true;
    }

    public int add(Pair<?, ?>... nameAndParams) {
        if (CheckUtils.isEmpty(nameAndParams)) {
            return 0;
        }
        int count = 0;
        for (Pair<?, ?> nameAndParam : nameAndParams) {
            if (add(nameAndParam)) {
                count++;
            }
        }
        return count;
    }

    public <T> T get(Object name, Class<T> clazz) {
        if (CheckUtils.hasNull(name, clazz)) {
            return null;
        }
        Object param = params.get(name);
        if (clazz.isInstance(param)) {
            return clazz.cast(param);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Query{" +
                "params=" + params +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(params, query.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params);
    }
}
