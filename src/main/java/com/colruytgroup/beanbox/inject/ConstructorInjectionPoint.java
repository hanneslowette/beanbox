package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.reflect.Constructor;

public class ConstructorInjectionPoint<T> extends AbstractInjectionPoint<T, Constructor<T>> {

    public ConstructorInjectionPoint(Bean<T> bean, AnnotatedConstructor<T> member) {
        super(bean, member);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<T> context) throws BeanBoxException {
        try {
            if (instance != null)
                throw new InjectionException("attempted to inject constructor of " + super.getType() + " but instance already present");

            AnnotatedConstructor<T> annotated = ReflectionUtil.cast(super.getAnnotated());
            Constructor<T> constructor = ReflectionUtil.cast(super.getMember());

            Object[] parameters = new Object[annotated.getParameters().size()];
            for (AnnotatedParameter<T> parameter : annotated.getParameters()) {
                if (parameters[parameter.getPosition()] != null)
                    throw new InjectionException("attempted to inject parameter for type " + super.getType() +
                            " but object already present for position " + parameter.getPosition());

                parameters[parameter.getPosition()] = manager.getInjectableReference(this, context);
            }

            context.push(constructor.newInstance(parameters));
        } catch (ReflectiveOperationException ex) {
            throw new BeanInstantiationException("could not create object for bean " + super.getType(), ex);
        }
    }

}
