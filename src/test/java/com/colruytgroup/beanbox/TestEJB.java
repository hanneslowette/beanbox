package com.colruytgroup.beanbox;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TestEJB {

    private boolean futureValue = false;

    @PersistenceContext(unitName = "test")
    private EntityManager test;

    @PostConstruct
    public void postConstructTest() {
        futureValue = true;
    }

    public boolean isFutureValue() {
        test.getTransaction();
        return futureValue;
    }
}
