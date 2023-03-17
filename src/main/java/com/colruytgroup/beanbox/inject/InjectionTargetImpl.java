package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxRuntimeException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import java.util.Set;

public class InjectionTargetImpl<T> implements InjectionTarget<T> {

    private final Bean<T> bean;
    private final Instantiator<T> instantiator;
    private final BeanManager manager;
    private final Set<InjectionPoint> injectionPoints;

    public InjectionTargetImpl(Bean<T> bean, Instantiator<T> instantiator, BeanManager manager, Set<InjectionPoint> injectionPoints) {
        this.bean = bean;
        this.instantiator = instantiator;
        this.manager = manager;
        this.injectionPoints = injectionPoints;
    }

    @Override
    public void inject(T t, CreationalContext<T> creationalContext) {

    }

    @Override
    public T produce(CreationalContext<T> creationalContext) {
        try {
            return instantiator.produce(creationalContext, manager);
        } catch (BeanInstantiationException ex) {
            throw new BeanBoxRuntimeException("could not create bean " + bean.getBeanClass(), ex);
        }
    }

    @Override
    public void postConstruct(T t) {

    }

    @Override
    public void preDestroy(T t) {

    }

    @Override
    public void dispose(T t) {

    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return injectionPoints;
    }

}
