package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.*;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InjectionPoints {

    public static <T> ConstructorInjectionPoint<T> discoverInjectablesConstructors(AnnotatedType<T> annotatedType, Bean<T> bean) throws IntrospectException {
        ConstructorInjectionPoint<T> constructorInjectionPoint = null;

        for (AnnotatedConstructor<T> constructor : annotatedType.getConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class) || constructor.getParameters().size() == 0) {
                if (constructorInjectionPoint != null) {
                    throw new IntrospectException("multiple constructor injection points found for class " + bean.getBeanClass());
                }
                List<ParameterInjectionPoint<T>> parameters = new LinkedList<>();
                for (AnnotatedParameter<T> parameter : constructor.getParameters()) {
                    parameters.add(new ParameterInjectionPoint<>(bean, parameter));
                }

                constructorInjectionPoint = new ConstructorInjectionPoint<>(bean, constructor, parameters);
            }
        }

        return constructorInjectionPoint;
    }

    public static <T> Set<AbstractInjectionPoint<T>> discoverAnnotatedMethodInjectionPoint(AnnotatedType<T> annotatedType, Bean<T> bean, Class<? extends Annotation> annotationClass) throws IntrospectException {
        Set<AbstractInjectionPoint<T>> injectionPoints = new HashSet<>();

        for (AnnotatedMethod<? super T> method : annotatedType.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation.annotationType().equals(annotationClass)) {
                    List<ParameterInjectionPoint<T>> parameters = new LinkedList<>();
                    for (AnnotatedParameter<? super T> parameter : method.getParameters()) {
                        parameters.add(new ParameterInjectionPoint<>(bean, ReflectionUtil.<AnnotatedParameter<T>> cast(parameter)));
                    }

                    injectionPoints.add(new MethodInjectionPoint<>(bean, ReflectionUtil.<AnnotatedMethod<T>> cast(method), parameters));
                    break;
                }
            }
        }

        return injectionPoints;
    }

    public static <T> Set<AbstractInjectionPoint<T>> discoverFieldInjectionPoints(AnnotatedType<T> annotatedType, Bean<T> bean) {
        Set<AbstractInjectionPoint<T>> injectionPoints = new HashSet<>();

        for (AnnotatedField<? super T> field : annotatedType.getFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType().equals(Inject.class)
                        || annotation.annotationType().equals(EJB.class)
                        || annotation.annotationType().equals(PersistenceContext.class)) {

                    injectionPoints.add(new FieldInjectionPoint<>(bean, ReflectionUtil.<AnnotatedField<T>> cast(field)));
                    break;
                }
            }
        }

        return injectionPoints;
    }

}
