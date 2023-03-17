package com.colruytgroup.beanbox.inject;

import javax.ejb.Stateless;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class DefaultBean<T> implements Bean<T> {

    private final AnnotatedType<T> type;
    private final BeanManager manager;
    private final Set<InjectionPoint> injectionPoints;

    public DefaultBean(AnnotatedType<T> type, BeanManager manager) {
        this.type = type;
        this.manager = manager;
        this.injectionPoints = new HashSet<>();
    }

    @Override
    public Set<Type> getTypes() {
        return type.getTypeClosure();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        Set<Annotation> qualifiers = new HashSet<>();
        for (Annotation annotation : type.getAnnotations()) {
            if (manager.isScope(annotation.annotationType())) {
                qualifiers.add(annotation);
            }
        }
        return qualifiers;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        Class<? extends Annotation> scope = null;
        for (Annotation annotation : type.getAnnotations()) {
            if (manager.isScope(annotation.annotationType()))
                scope = annotation.annotationType();
        }
        return scope == null ? Stateless.class : scope;
    }

    @Override
    public String getName() {
        return getBeanClass().getName();
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return null;
    }

    @Override
    public Class<?> getBeanClass() {
        return type.getJavaClass();
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
        return injectionPoints;
    }

    @Override
    public T create(CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public void destroy(T t, CreationalContext<T> creationalContext) {

    }

    public AnnotatedType<T> getType() {
        return type;
    }
}
