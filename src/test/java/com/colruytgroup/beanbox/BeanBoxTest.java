package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.inject.AnnotatedHelper;
import org.junit.Test;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertTrue;

//@RunWith(BeanBoxRunner.class)
public class BeanBoxTest {

    @Test
    public void beforeTest() throws IntrospectException {
        AnnotatedType<TestEntity> annotated = AnnotatedHelper.introspect(TestEntity.class);

        for (AnnotatedField<?> field : annotated.getFields()) {
            assertTrue(field.isAnnotationPresent(PersistenceContext.class) || field.isAnnotationPresent(EJB.class));
        }
    }

}
