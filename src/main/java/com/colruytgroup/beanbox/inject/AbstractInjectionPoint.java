package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Set;

public abstract class AbstractInjectionPoint<T, M extends Member> implements InjectionPoint {

    private final Bean<T> bean;
    private final AnnotatedMember<T> member;

    public AbstractInjectionPoint(Bean<T> bean, AnnotatedMember<T> member) {
        this.bean = bean;
        this.member = member;
    }

    /**
     * Attempts to inject this injectionpoint
     *
     * @param instance
     * @param manager
     * @param context
     * @throws InjectionException
     */
    public abstract void inject(Object instance, BeanManager manager, CreationalContext<?> context) throws InjectionException;

    @Override
    public Type getType() {
        return bean.getBeanClass();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        return bean.getQualifiers();
    }

    @Override
    public Bean<?> getBean() {
        return this.bean;
    }

    @Override
    public Member getMember() {
        return member.getJavaMember();
    }

    @Override
    public Annotated getAnnotated() {
        return member;
    }

    @Override
    public boolean isDelegate() {
        return false; // Delegates not supported
    }

    @Override
    public boolean isTransient() {
        return false; // Transient not supported
    }

}
