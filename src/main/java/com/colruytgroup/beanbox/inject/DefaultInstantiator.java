package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;

public class DefaultInstantiator<T> implements Instantiator<T> {

    /**
     * The injection point that creates the instance
     */
    private final ConstructorInjectionPoint<T> injectionPoint;

    public DefaultInstantiator(ConstructorInjectionPoint<T> injectionPoint) {
        this.injectionPoint = injectionPoint;
    }

    @Override
    public T produce(CreationalContext<T> creationalContext, BeanManager manager) throws BeanInstantiationException {
        try {
            return injectionPoint.newInstance(creationalContext, manager);
        } catch (Exception ex) {
            throw new BeanInstantiationException("Could not instantiate the ", ex);
        }
    }
}
