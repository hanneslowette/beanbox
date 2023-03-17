package com.colruytgroup.beanbox;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TestEntity {

    @PersistenceContext(unitName = "test")
    private EntityManager manager;

    private final TestEJB test;

    @EJB
    private TestEJB fieldInjected;

    @Inject
    public TestEntity(TestEJB test) {
        this.test = test;
    }

    public void test(int test) {

    }

    public EntityManager getManager() {
        return manager;
    }

    public TestEJB getTest() {
        return test;
    }

    public TestEJB getFieldInjected() {
        return fieldInjected;
    }

    public void setFieldInjected(TestEJB fieldInjected) {
        if (fieldInjected != null)
            this.fieldInjected = fieldInjected;
    }
}
