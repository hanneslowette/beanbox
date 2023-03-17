package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanInstantiationException;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;

public class FieldInjectionPoint<T> extends AbstractInjectionPoint<T> {

    public FieldInjectionPoint(Bean<T> bean, AnnotatedField<T> member) {
        super(bean, member);
    }

    @Override
    public void inject(Object instance, BeanManager manager, CreationalContext<T> context) throws BeanBoxException {
        try {
            if (instance == null) {
                throw new InjectionException("attempting to inject fields for " + super.getType() + " but instance was null");
            } else if (super.getBean() == null) {
                throw new InjectionException("can not inject object " + instance.getClass() + " no bean present");
            } else if (!instance.getClass().isAssignableFrom(super.getBean().getBeanClass())) {
                throw new InjectionException("attempting to inject fields for " + super.getType() + " but instance was of type " + instance.getClass());
            }

            Field field = ReflectionUtil.cast(super.getMember());
            Bean<?> bean = manager.resolve(manager.getBeans(field.getType()));

            if (field.isAnnotationPresent(PersistenceContext.class)) { // ugly but does the job
                PersistenceContext persistenceContext = field.getAnnotation(PersistenceContext.class);

                if (persistenceContext.unitName() == null) {
                    throw new InjectionException("could not inject persistence context for " + bean.getBeanClass() + " unitName is null");
                }

                field.set(instance, ((BeanBoxBeanManager) manager).getPersistenceReference(persistenceContext.unitName()));
            } else {
                field.set(instance, manager.getReference(bean, field.getType(), context));
            }
        } catch (ReflectiveOperationException ex) {
            throw new BeanInstantiationException("could not inject fields for bean " + super.getType());
        }
    }

}
