package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.context.MockitoContext;
import com.colruytgroup.beanbox.context.PersistenceContext;
import com.colruytgroup.beanbox.context.StatefulContext;
import com.colruytgroup.beanbox.context.StatelessContext;
import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.inject.BeanBoxBeanManager;

public class BeanBox {

    private final BeanBoxBeanManager manager;

    private BeanBox(BeanBoxBeanManager manager) {
        this.manager = manager;
    }

    public static BeanBox create() throws BeanBoxException {
        BeanBoxBeanManager manager = new BeanBoxBeanManager();

        manager.addContext(new MockitoContext());
        manager.addContext(new StatefulContext());
        manager.addContext(new StatelessContext());
        manager.addContext(new PersistenceContext());

        return new BeanBox(manager);
    }

}