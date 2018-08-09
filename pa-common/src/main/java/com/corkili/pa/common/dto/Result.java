package com.corkili.pa.common.dto;

import java.util.Objects;

public final class Result<Extra> implements Cloneable {

    private final boolean success;
    private final String message;
    private final Extra extra;
    private final long createTimestamp;

    public Result(boolean success, String message, Extra extra) {
        this.createTimestamp = System.currentTimeMillis();
        this.success = success;
        this.message = message;
        this.extra = extra;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Extra getExtra() {
        return extra;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    @Override
    protected Result clone() {
        return new Result<>(success, message, extra);
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", extra=" + extra +
                ", createTimestamp=" + createTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return success == result.success &&
                createTimestamp == result.createTimestamp &&
                Objects.equals(message, result.message) &&
                Objects.equals(extra, result.extra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, extra, createTimestamp);
    }
}
