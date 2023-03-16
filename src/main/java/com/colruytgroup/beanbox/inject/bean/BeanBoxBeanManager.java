package com.colruytgroup.beanbox.inject.bean;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public class BeanBoxBeanManager extends AbstractBeanManager {

    @Override
    public Object getReference(Bean<?> bean, Type type, CreationalContext<?> creationalContext) {
        return null;
    }

    @Override
    public Object getInjectableReference(InjectionPoint injectionPoint, CreationalContext<?> creationalContext) {
        return null;
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(Type type, Annotation... annotations) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(String s) {
        return null;
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> set) {
        return null;
    }

    @Override
    public void validate(InjectionPoint injectionPoint) {

    }

    @Override
    public boolean isScope(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isQualifier(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isStereotype(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public Context getContext(Class<? extends Annotation> aClass) {
        return null;
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        return null;
    }

}
