package com.corkili.pa.server.exception;

public class UnsupportedSeverTypeException extends RuntimeException {

    public UnsupportedSeverTypeException() {
    }

    public UnsupportedSeverTypeException(String message) {
        super(message);
    }

    public UnsupportedSeverTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedSeverTypeException(Throwable cause) {
        super(cause);
    }

    public UnsupportedSeverTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
