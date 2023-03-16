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
    private final BeanContainer container;

    public BeanBoxBeanManager() {
        this.contextMap = new HashMap<>();
        this.container = new BeanContainerImpl();
    }

    public void addContext(Context context) {
        if (contextMap.containsKey(context.getScope())) {
            throw new IllegalStateException("duplicate scope detected");
        }
        this.contextMap.put(context.getScope(), context);
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> set) {
        return container.resolve(set);
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> aClass) {
        return AnnotatedHelper.introspect(aClass);
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        return null;
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
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
    public void validate(InjectionPoint injectionPoint) {

    }

    @Override
    public Set<Bean<?>> getBeans(Type type, Annotation... annotations) {
        return container.lookup(type, annotations);
    }

    @Override
    public Set<Bean<?>> getBeans(String s) {
        return container.forName(s);
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> annotation) {
        return true; // all scopes are normal scopes in this implementation
    }

    @Override
    public boolean isScope(Class<? extends Annotation> annotation) {
        return contextMap.containsKey(annotation);
    }

    @Override
    public Context getContext(Class<? extends Annotation> scope) {
        return this.contextMap.get(scope);
    }

}
