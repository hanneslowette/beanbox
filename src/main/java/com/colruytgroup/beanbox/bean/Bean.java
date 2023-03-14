package com.colruytgroup.beanbox.bean;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.interceptor.InvocationContext;
import java.util.Set;

public interface Bean<T> {

    /**
     *
     * @return
     */
    Class<T> getBeanType();

    /**
     *
     * @return
     */
    Set<InjectionPoint> getInjectionPoints();



}
