package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import java.util.Set;

public abstract class BeanInjectionTarget<T> implements InjectionTarget<T> {

    @Override
    public void inject(T t, CreationalContext<T> creationalContext) {

    }

    @Override
    public T produce(CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return null;
    }

}
