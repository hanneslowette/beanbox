package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.IntrospectException;

import javax.enterprise.inject.spi.*;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public final class InjectionTargets {

    private InjectionTargets(){}

    public static <T> InjectionTarget<T> createInjectionTarget(Bean<T> bean, AnnotatedType<T> annotatedType, BeanManager manager) throws IntrospectException {
        ConstructorInjectionPoint<T> constructorInjectionPoint = InjectionPoints.discoverInjectablesConstructors(annotatedType, bean);
        Set<AbstractInjectionPoint<T>> injectionPoints = new HashSet<>();

        injectionPoints.addAll(InjectionPoints.discoverFieldInjectionPoints(annotatedType, bean));
        injectionPoints.addAll(InjectionPoints.discoverAnnotatedMethodInjectionPoint(annotatedType, bean, Inject.class));

        return new EagerInjectionTarget<>(annotatedType, bean, new DefaultInstantiator<>(bean, constructorInjectionPoint), manager, injectionPoints);
    }

}
