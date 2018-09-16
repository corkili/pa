package com.corkili.pa.dao.exception;

public class UnsupportedStateException extends RuntimeException {

    public UnsupportedStateException() {
    }

    public UnsupportedStateException(String message) {
        super(message);
    }

    public UnsupportedStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedStateException(Throwable cause) {
        super(cause);
    }

    public UnsupportedStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
