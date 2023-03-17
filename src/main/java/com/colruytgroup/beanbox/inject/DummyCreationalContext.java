package com.colruytgroup.beanbox.inject;

import javax.enterprise.context.spi.CreationalContext;

/**
 * Dummy implementation as we forcefully create instances of objects rather than
 * chunk the creation. We do not need to push incomplete instances or release anything
 * as the container scope is either a test or a test case
 *
 * @param <T>
 */
public class DummyCreationalContext<T> implements CreationalContext<T> {

    @Override
    public void push(T t) {

    }

    @Override
    public void release() {

    }

}
