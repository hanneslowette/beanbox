package com.colruytgroup.beanbox.context;

import javax.ejb.Stateful;
import java.lang.annotation.Annotation;

public class StatefulContext extends AbstractContext {

    @Override
    public Class<? extends Annotation> getScope() {
        return Stateful.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
