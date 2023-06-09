package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.exception.BeanBoxException;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

//@RunWith(BeanBoxRunner.class)
public class BeanBoxTest {

    @Inject
    private TestEntity entity;

    @PersistenceContext(unitName = "test")
    private EntityManager manager;

    @Before
    public void initialize() throws BeanBoxException {
        BeanBox.initialize(this);
    }

    @Test
    public void checkCreation() {
        assertNotNull(entity);
        assertNotNull(entity.getTest());
        assertNotNull(entity.getManager());
        assertNotNull(entity.getFieldInjected());

        assertTrue(entity.getTest().isFutureValue());
    }

    @Test
    public void checkEquality() {
        assertEquals(manager, entity.getManager());
        assertEquals(entity.getTest(), entity.getFieldInjected());
    }

}
