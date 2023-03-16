package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

public class CreationalContextImpl<T> implements CreationalContext<T> {

//    private final Contextual<T> contextual;

    @Override
    public void push(T t) {

    }

    @Override
    public void release() {

    }

}
