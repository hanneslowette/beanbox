package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import java.util.Set;

public class InjectionTargetImpl<T> implements InjectionTarget<T> {

    private final Bean<T> bean;
    private final Instantiator<T> instantiator;
    private final BeanContainer container;
    private final BeanManager manager;
    private final Set<InjectionPoint> injectionPoints;

    public InjectionTargetImpl(Bean<T> bean, Instantiator<T> instantiator, BeanContainer container, BeanManager manager, Set<InjectionPoint> injectionPoints) {
        this.bean = bean;
        this.instantiator = instantiator;
        this.container = container;
        this.manager = manager;
        this.injectionPoints = injectionPoints;
    }

    @Override
    public void inject(T t, CreationalContext<T> creationalContext) {

    }

    @Override
    public T produce(CreationalContext<T> creationalContext) {
        return instantiator.produce(creationalContext, manager);
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
        return null;
    }

}
