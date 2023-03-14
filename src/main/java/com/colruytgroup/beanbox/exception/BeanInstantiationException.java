package com.colruytgroup.beanbox.exception;

public class BeanInstantiationException extends BeanBoxException {
    public BeanInstantiationException(String message) {
        super(message);
    }
    public BeanInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
