package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.inject.bean.BeanContainer;

import javax.enterprise.context.spi.CreationalContext;

public interface InjectionContext<T> extends CreationalContext<T> {

    BeanContainer getBeanContainer();

    Instantiator<?> getInstantiator();

    Injector<?> getInjector();

}
