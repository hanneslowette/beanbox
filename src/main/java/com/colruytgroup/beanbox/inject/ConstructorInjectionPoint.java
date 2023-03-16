package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.AnnotatedMember;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.reflect.Constructor;

public class ConstructorInjectionPoint<T> extends AbstractInjectionPoint<T, Constructor<T>> {

    public ConstructorInjectionPoint(Bean<T> bean, AnnotatedMember<Constructor<T>> member) {
        super(bean, member);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<?> context) throws InjectionException {

    }

}
