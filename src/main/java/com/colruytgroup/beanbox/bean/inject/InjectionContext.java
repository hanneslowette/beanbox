package com.colruytgroup.beanbox.bean.inject;

import com.colruytgroup.beanbox.bean.BeanContainer;

import javax.enterprise.context.spi.CreationalContext;

public interface InjectionContext<T> extends CreationalContext<T> {

    BeanContainer getBeanContainer();

    Instantiator<?> getInstantiator();

    Injector<?> getInjector();

}
