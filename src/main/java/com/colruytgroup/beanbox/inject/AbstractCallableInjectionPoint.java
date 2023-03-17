package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.*;
import java.util.List;

public abstract class AbstractCallableInjectionPoint<T> extends AbstractInjectionPoint<T> {

    private final List<ParameterInjectionPoint<T>> parameters;

    public AbstractCallableInjectionPoint(Bean<T> bean, AnnotatedMember<T> member, List<ParameterInjectionPoint<T>> parameters) {
        super(bean, member);
        this.parameters = parameters;
    }

    public Object[] fetchParameters(AnnotatedCallable<T> annotated, BeanManager manager, CreationalContext<T> context) {
        Object[] parameters = new Object[annotated.getParameters().size()];
        for (ParameterInjectionPoint<T> injectionPoint : this.parameters) {
            AnnotatedParameter<T> annotatedParameter = injectionPoint.getAnnotated();

            if (annotatedParameter.getPosition() < 0 || annotatedParameter.getPosition() >= parameters.length) {
                throw new InjectionException("parameter index out of bounds, expected [0, " + parameters.length +
                        "], was " + annotatedParameter.getPosition());
            }
            if (parameters[annotatedParameter.getPosition()] != null) {
                throw new InjectionException("attempted to inject parameter for type " + super.getType() +
                        " but object already present for position " + annotatedParameter.getPosition());
            }

            parameters[annotatedParameter.getPosition()] = manager.getInjectableReference(injectionPoint, context);
        }
        return parameters;
    }

}
