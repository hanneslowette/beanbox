package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Set;

public class ParameterInjectionPoint<T> implements InjectionPoint {

    private final Bean<T> bean;
    private final AnnotatedParameter<T> parameter;

    public ParameterInjectionPoint(Bean<T> bean, AnnotatedParameter<T> parameter) {
        this.bean = bean;
        this.parameter = parameter;
    }

    public void inject(BeanManager manager, CreationalContext<T> context) {

    }


    @Override
    public Type getType() {
        return parameter.getBaseType();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return parameter.getAnnotations();
    }

    @Override
    public Bean<?> getBean() {
        return bean;
    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public AnnotatedParameter<T> getAnnotated() {
        return parameter;
    }

    @Override
    public boolean isDelegate() {
        return false;
    }

    @Override
    public boolean isTransient() {
        return false;
    }
}
