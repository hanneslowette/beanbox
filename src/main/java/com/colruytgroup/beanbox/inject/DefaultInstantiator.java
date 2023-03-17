package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

public class DefaultInstantiator<T> implements Instantiator<T> {

    private final Logger logger = LoggerFactory.getLogger(Instantiator.class);

    private final ConstructorInjectionPoint<T> injectionPoint;
    private final Bean<T> bean;

    public DefaultInstantiator(Bean<T> bean, ConstructorInjectionPoint<T> injectionPoint) {
        this.bean = bean;
        this.injectionPoint = injectionPoint;
    }

    @Override
    public T produce(CreationalContext<T> creationalContext, BeanManager manager) throws BeanInstantiationException {
        try {
            logger.debug("creating new instance for bean of type " + bean.getBeanClass());
            return injectionPoint.newInstance(creationalContext, manager);
        } catch (Exception ex) {
            throw new BeanInstantiationException("Could not instantiate the ", ex);
        }
    }
}
