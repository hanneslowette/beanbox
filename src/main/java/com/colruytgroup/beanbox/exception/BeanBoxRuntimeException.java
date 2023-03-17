package com.colruytgroup.beanbox.exception;

public class BeanBoxRuntimeException extends RuntimeException{
    public BeanBoxRuntimeException() {
        super();
    }

    public BeanBoxRuntimeException(String message) {
        super(message);
    }

    public BeanBoxRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanBoxRuntimeException(Throwable cause) {
        super(cause);
    }
}
