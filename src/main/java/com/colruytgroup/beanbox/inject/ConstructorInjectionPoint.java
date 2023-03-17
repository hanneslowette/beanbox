package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedMember;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.reflect.Constructor;
import java.util.List;

public class ConstructorInjectionPoint<T> extends AbstractCallableInjectionPoint<T> {

    public ConstructorInjectionPoint(Bean<T> bean, AnnotatedMember<T> member, List<ParameterInjectionPoint<T>> parameters) {
        super(bean, member, parameters);
    }

    /**
     * Attempts to create a new instance of a class by injecting the parameters of the given constructor
     *
     * @param context The creational context
     * @param manager The bean manager
     * @return A newly created instance
     * @throws ReflectiveOperationException When something happens when attempting to inject the parameters
     */
    public T newInstance(CreationalContext<T> context, BeanManager manager) throws ReflectiveOperationException {
        AnnotatedConstructor<T> annotated = ReflectionUtil.cast(super.getAnnotated());
        Constructor<T> constructor = ReflectionUtil.cast(super.getMember());
        Object[] parameters = super.fetchParameters(annotated, manager, context);

        return constructor.newInstance(parameters);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<T> context) throws BeanBoxException {
        // do nothing
    }
}

