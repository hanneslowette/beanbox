package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.context.MockitoContext;
import com.colruytgroup.beanbox.context.StatefulContext;
import com.colruytgroup.beanbox.context.StatelessContext;
import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.inject.BeanBoxBeanManager;
import com.colruytgroup.beanbox.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionTarget;

public class BeanBox {

    private static final Logger logger = LoggerFactory.getLogger(BeanBox.class);

    private static final BeanBoxBeanManager manager;

    static {
        manager = new BeanBoxBeanManager();
        manager.registerContext(new MockitoContext());
        manager.registerContext(new StatefulContext());
        manager.registerContext(new StatelessContext(manager));
    }

    public static void initialize(Object source) throws BeanBoxException {
        AnnotatedType<Object> annotated = ReflectionUtil.cast(manager.createAnnotatedType(source.getClass()));
        Bean<Object> bean = manager.introspect(annotated);

        CreationalContext<Object> context = manager.createCreationalContext(bean);
        InjectionTarget<Object> target = manager.createInjectionTarget(annotated);

        target.inject(source, context);
    }

    public static void beginTransaction(String... unitNames) {
        for (String name : unitNames) {
            logger.info("beginning transaction for unit {}", name);
            manager.getPersistenceReference(name).getTransaction().begin();
        }
    }

    public static void commitTransaction(String... unitNames) {
        for (String name : unitNames) {
            logger.info("commiting transaction for unit {}", name);
            manager.getPersistenceReference(name).getTransaction().commit();
        }
    }

}