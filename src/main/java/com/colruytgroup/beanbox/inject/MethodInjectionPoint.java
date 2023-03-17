package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.reflect.Method;
import java.util.List;

public class MethodInjectionPoint<T> extends AbstractCallableInjectionPoint<T> {

    public MethodInjectionPoint(Bean<T> bean, AnnotatedMethod<T> member, List<ParameterInjectionPoint<T>> parameters) {
        super(bean, member, parameters);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<T> context) throws BeanBoxException {
        try {
            Object[] parameters = super.fetchParameters(ReflectionUtil.<AnnotatedMethod<T>> cast(super.getAnnotated()), manager, context);
            Method method = (Method) super.getMember();

            method.invoke(instance, parameters);
        } catch (ReflectiveOperationException ex) {
            throw new BeanInstantiationException("could not invoke method " + super.getMember(), ex);
        }
    }

}
