package com.colruytgroup.beanbox.context;

import com.colruytgroup.beanbox.inject.DummyCreationalContext;

import javax.ejb.Stateful;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;

public class StatefulContext implements Context {

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return contextual.create(new DummyCreationalContext<T>());
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Stateful.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
