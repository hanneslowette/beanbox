package com.colruytgroup.beanbox.context;

import java.lang.annotation.Annotation;

public class PersistenceContext extends AbstractContext {

    @Override
    public Class<? extends Annotation> getScope() {
        return javax.persistence.PersistenceContext.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
