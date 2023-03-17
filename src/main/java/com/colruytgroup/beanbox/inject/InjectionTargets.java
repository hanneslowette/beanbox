package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.inject.spi.*;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class InjectionTargets {

    private InjectionTargets(){}

    public static <T> InjectionTarget<T> createInjectionTarget(Bean<T> bean, AnnotatedType<T> annotatedType, BeanManager manager) throws IntrospectException {
        Set<InjectionPoint> injectionPoints = new HashSet<>();
        ConstructorInjectionPoint<T> constructorInjectionPoint = null;

        for (AnnotatedConstructor<T> constructor : annotatedType.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class) || constructor.getParameters().size() == 0) {
                List<ParameterInjectionPoint<T>> parameters = new LinkedList<>();
                for (AnnotatedParameter<T> parameter : constructor.getParameters()) {
                    parameters.add(new ParameterInjectionPoint<T>(bean, parameter));
                }

                constructorInjectionPoint = new ConstructorInjectionPoint<>(bean, constructor, parameters);
            }
        }

        if (constructorInjectionPoint == null) {
            throw new IntrospectException("no injectable or default constructor found, annotate the injectable constructor with @Inject or create a default constructor");
        }

        for (AnnotatedMethod<? super T> method : annotatedType.getMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                // TODO: Add method injection points
            }
        }

        for (AnnotatedField<? super T> field : annotatedType.getFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (manager.isScope(annotation.annotationType()) && manager.isNormalScope(annotation.annotationType())) {
                    injectionPoints.add(new FieldInjectionPoint<>(bean, ReflectionUtil.<AnnotatedField<T>> cast(field)));
                }
            }
        }

        return new InjectionTargetImpl<>(bean, new DefaultInstantiator<>(constructorInjectionPoint), manager, injectionPoints);
    }

}
