package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.InjectionTarget;

public abstract class AbstractInjectionTarget<T> implements InjectionTarget<T> {

    @Override
    public void preDestroy(T t) {

    }

    @Override
    public void dispose(T t) {

    }

}
