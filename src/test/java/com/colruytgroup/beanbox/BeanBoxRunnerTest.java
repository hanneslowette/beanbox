package com.colruytgroup.beanbox;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(BeanBoxRunner.class)
public class BeanBoxRunnerTest {

    @Inject
    private TestEntity entity;

    @PersistenceContext(unitName = "test")
    private EntityManager manager;

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
