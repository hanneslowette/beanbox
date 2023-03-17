package com.colruytgroup.beanbox.context;

import org.mockito.Mock;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;

/**
 * TODO: Inject mocks into places where the same element as the mocked object is
 * TODO: in the test source class
 */
public class MockitoContext implements Context {

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return null;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Mock.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
