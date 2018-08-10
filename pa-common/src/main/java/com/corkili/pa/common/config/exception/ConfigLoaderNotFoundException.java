package com.corkili.pa.common.config.exception;

public class ConfigLoaderNotFoundException extends RuntimeException {

    public ConfigLoaderNotFoundException(String message) {
        super(message);
    }

    public ConfigLoaderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
