package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanBoxRuntimeException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.*;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * An injection target implementation that will eagerly create a new instance of each bean
 *
 * @param <T>
 */
public class EagerInjectionTarget<T> extends AbstractInjectionTarget<T> {

    private final AnnotatedType<T> type;
    private final Bean<T> bean;
    private final Instantiator<T> instantiator;
    private final BeanManager manager;
    private final Set<AbstractInjectionPoint<T>> injectionPoints;

    public EagerInjectionTarget(AnnotatedType<T> type, Bean<T> bean, Instantiator<T> instantiator, BeanManager manager, Set<AbstractInjectionPoint<T>> injectionPoints) {
        this.type = type;
        this.bean = bean;
        this.instantiator = instantiator;
        this.manager = manager;
        this.injectionPoints = injectionPoints;
    }

    @Override
    public void inject(T t, CreationalContext<T> creationalContext) {
        try {
            for (AbstractInjectionPoint<T> injectionPoint : injectionPoints) {
                injectionPoint.inject(t, manager, creationalContext);
            }
        } catch (BeanBoxException e) {
            throw new BeanBoxRuntimeException("could not inject " + bean.getBeanClass(), e);
        }
    }

    @Override
    public T produce(CreationalContext<T> creationalContext) {
        try {
            return instantiator.produce(creationalContext, manager);
        } catch (BeanInstantiationException ex) {
            throw new BeanBoxRuntimeException("could not create bean " + bean.getBeanClass(), ex);
        }
    }

    @Override
    public void postConstruct(T instance) {
        callMethodsAnnotatedWith(instance, PostConstruct.class);
    }

    @Override
    public void preDestroy(T instance) {
        callMethodsAnnotatedWith(instance, PreDestroy.class);
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return ReflectionUtil.cast(injectionPoints);
    }

    private void callMethodsAnnotatedWith(T instance, Class<? extends Annotation> annotationClass) {
        try {
            for (AbstractInjectionPoint<T> injectionPoint : InjectionPoints.discoverAnnotatedMethodInjectionPoint(type, bean, annotationClass)) {
                if (injectionPoint instanceof MethodInjectionPoint) {
                    MethodInjectionPoint<T> method = ReflectionUtil.cast(injectionPoint);
                    AnnotatedMethod<T> annotated = ReflectionUtil.cast(injectionPoint.getAnnotated());

                    if (annotated.isAnnotationPresent(PostConstruct.class)) {
                        method.inject(instance, manager, null);
                    }
                }
            }
        } catch (BeanBoxException e) {
            throw new BeanBoxRuntimeException("could not inject " + bean.getBeanClass(), e);
        }
    }

}
