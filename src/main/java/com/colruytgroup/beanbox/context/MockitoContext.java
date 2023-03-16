package com.colruytgroup.beanbox.context;

import org.mockito.Mock;

import java.lang.annotation.Annotation;

public class MockitoContext extends AbstractContext {

    @Override
    public Class<? extends Annotation> getScope() {
        return Mock.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
