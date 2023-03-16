package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.reflect.Field;

public class FieldInjectionPoint<T> extends AbstractInjectionPoint<T, Field> {

    public FieldInjectionPoint(Bean<T> bean, AnnotatedField<T> member) {
        super(bean, member);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<T> context) throws BeanBoxException {
        try {
            if (instance == null) {
                throw new InjectionException("attempting to inject fields for " + super.getType() + " but instance was null");
            } else if (!instance.getClass().isAssignableFrom(super.getBean().getBeanClass())) {
                throw new InjectionException("attempting to inject fields for " + super.getType() + " but instance was of type " + instance.getClass());
            }

            Field field = ReflectionUtil.cast(super.getMember());
            field.set(instance, manager.getInjectableReference(this, context));
        } catch (ReflectiveOperationException ex) {
            throw new BeanInstantiationException("could not inject fields for bean " + super.getType());
        }
    }

}
