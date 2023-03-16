package com.colruytgroup.beanbox;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TestEntity {

    @PersistenceContext(unitName = "test")
    private EntityManager manager;

    @EJB
    private TestEJB test;

    @Inject
    public TestEntity() {

    }

    public void test(int test) {

    }

}
