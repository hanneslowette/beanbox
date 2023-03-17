package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanInstantiationException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;

public interface Instantiator<T> {

    /**
     * Produces a new instance of the given type
     * @param creationalContext
     * @param manager
     * @return
     */
    T produce(CreationalContext<T> creationalContext, BeanManager manager) throws BeanInstantiationException;

}
