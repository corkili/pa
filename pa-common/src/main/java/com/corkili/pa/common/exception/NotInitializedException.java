package com.corkili.pa.common.exception;

public class NotInitializedException extends RuntimeException {

    public NotInitializedException() {
    }

    public NotInitializedException(String message) {
        super(message);
    }

    public NotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInitializedException(Throwable cause) {
        super(cause);
    }

    public NotInitializedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
