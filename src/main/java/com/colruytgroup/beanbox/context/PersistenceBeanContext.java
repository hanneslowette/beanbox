package com.colruytgroup.beanbox.context;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PersistenceBeanContext {

    private final Map<String, EntityManager> managers;

    public PersistenceBeanContext() {
        this.managers = new HashMap<>();
    }

    public EntityManager create(String unitName) {
        if (managers.containsKey(unitName)) {
            return managers.get(unitName);
        }
        EntityManager manager = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        managers.put(unitName, manager);
        return manager;
    }

}
