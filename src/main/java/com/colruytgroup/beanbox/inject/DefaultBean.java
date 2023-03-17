package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public class DefaultBean<T> implements Bean<T> {

    private final Class<T> type;

    public DefaultBean(Class<T> type) {
        this.type = type;
    }

    @Override
    public Set<Type> getTypes() {
        return null;
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return null;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return null;
    }

    @Override
    public Class<?> getBeanClass() {
        return type;
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return null;
    }

    @Override
    public T create(CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public void destroy(T t, CreationalContext<T> creationalContext) {

    }
}
