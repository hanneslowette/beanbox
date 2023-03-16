package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanBoxBeanManager extends AbstractBeanManager {

    private final Map<Class<? extends Annotation>, Context> contextMap;

    public BeanBoxBeanManager() {
        this.contextMap = new HashMap<>();
    }

    public void addContext(Context context) {
        if (contextMap.containsKey(context.getScope())) {
            throw new IllegalStateException("duplicate scope detected");
        }
        this.contextMap.put(context.getScope(), context);
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        return null;
    }

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
    public boolean isScope(Class<? extends Annotation> annotation) {
        return contextMap.containsKey(annotation);
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> annotation) {
        return true; // all scopes are normal scopes in this implementation
    }

    @Override
    public Context getContext(Class<? extends Annotation> scope) {
        return this.contextMap.get(scope);
    }

}
