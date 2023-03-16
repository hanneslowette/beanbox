package com.colruytgroup.beanbox.context;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;

public abstract class AbstractContext implements Context {

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return null;
    }

}
