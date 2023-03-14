package com.colruytgroup.beanbox.bean.inject;

import com.colruytgroup.beanbox.exception.BeanInstantiationException;

public interface Instantiator<T> {

    /**
     *
     * @return
     * @throws BeanInstantiationException
     */
    T createInstance(Class<T> type) throws BeanInstantiationException;

}
