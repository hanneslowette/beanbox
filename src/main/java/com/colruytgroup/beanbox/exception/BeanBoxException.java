package com.colruytgroup.beanbox.exception;

public class BeanBoxException extends ReflectiveOperationException {
    public BeanBoxException(String message) {
        super(message);
    }
    public BeanBoxException(String message, Throwable cause) {
        super(message, cause);
    }
    public BeanBoxException(Throwable cause) {
        super(cause);
    }
}
