package com.colruytgroup.beanbox.exception;

public class IntrospectException extends BeanBoxException {
    public IntrospectException(String message) {
        super(message);
    }

    public IntrospectException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntrospectException(Throwable cause) {
        super(cause);
    }
}
