package com.colruytgroup.beanbox.context;

import javax.ejb.Stateless;
import java.lang.annotation.Annotation;

public class StatelessContext extends AbstractContext {

    @Override
    public Class<? extends Annotation> getScope() {
        return Stateless.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
