package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;

import javax.enterprise.inject.spi.InjectionPoint;

public interface InjectionPointValidator<IP extends InjectionPoint> {

    /**
     * Attempts to validate the injectionpoint
     *
     * @param injectionPoint The injectionpoint to be validated
     * @throws BeanBoxException Exceptions should be thrown the moment the injectionpoint is non-confirm
     */
    void validate(IP injectionPoint) throws BeanBoxException;

}
