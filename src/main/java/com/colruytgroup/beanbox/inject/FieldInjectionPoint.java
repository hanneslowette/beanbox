package com.colruytgroup.beanbox.inject;

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
    public void inject(Object instance, BeanManager manager, CreationalContext<?> context) throws InjectionException {

    }

}
