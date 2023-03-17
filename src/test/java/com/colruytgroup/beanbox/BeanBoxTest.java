package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.inject.*;
import org.junit.Test;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//@RunWith(BeanBoxRunner.class)
public class BeanBoxTest {

    @Test
    public void beforeTest() throws IntrospectException {
        AnnotatedType<TestEntity> annotated = AnnotatedHelper.introspect(TestEntity.class);
        Bean<TestEntity> bean = new DefaultBean<>(TestEntity.class);
        InjectionTarget<TestEntity> target = InjectionTargets.createInjectionTarget(bean, annotated, new BeanBoxBeanManager());
        TestEntity entity = target.produce(new CreationalContextImpl<TestEntity>());

        assertNotNull(entity);
        assertNotNull(entity.getTest());
        assertNotNull(entity.getManager());
        assertNotNull(entity.getFieldInjected());
    }

}
