package com.colruytgroup.beanbox.inject;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.inject.spi.*;
import javax.enterprise.inject.spi.Bean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public abstract class AbstractBeanManager implements BeanManager {

    @Override
    public boolean isQualifier(Class<? extends Annotation> annotation) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public boolean isStereotype(Class<? extends Annotation> annotation) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public Bean<?> getPassivationCapableBean(String s) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public void fireEvent(Object o, Annotation... annotations) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T t, Annotation... annotations) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public List<Decorator<?>> resolveDecorators(Set<Type> set, Annotation... annotations) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public List<Interceptor<?>> resolveInterceptors(InterceptionType interceptionType, Annotation... annotations) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public boolean isPassivatingScope(Class<? extends Annotation> aClass) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public boolean isInterceptorBinding(Class<? extends Annotation> aClass) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> aClass) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> aClass) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public ELResolver getELResolver() {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

    @Override
    public void validate(InjectionPoint injectionPoint) {
        throw new UnsupportedOperationException("method is not within scope of the project");
    }

}
