package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.*;
import javax.persistence.Parameter;
import java.lang.reflect.Constructor;
import java.util.List;

public class ConstructorInjectionPoint<T> extends AbstractInjectionPoint<T, Constructor<T>> {

    private final List<ParameterInjectionPoint<T>> parameters;

    public ConstructorInjectionPoint(Bean<T> bean, AnnotatedMember<T> member, List<ParameterInjectionPoint<T>> parameters) {
        super(bean, member);
        this.parameters = parameters;
    }

    /**
     * Attempts to create a new instance of a class by injecting the parameters of the given constructor
     *
     * @param context
     * @param manager
     * @return
     * @throws ReflectiveOperationException
     */
    public T newInstance(CreationalContext<T> context, BeanManager manager) throws ReflectiveOperationException {
        AnnotatedConstructor<T> annotated = ReflectionUtil.cast(super.getAnnotated());
        Constructor<T> constructor = ReflectionUtil.cast(super.getMember());

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

        return constructor.newInstance(parameters);
    }



}
