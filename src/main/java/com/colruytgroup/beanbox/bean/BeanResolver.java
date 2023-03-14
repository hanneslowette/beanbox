package com.colruytgroup.beanbox.bean;

import com.colruytgroup.beanbox.exception.IntrospectException;

import java.util.Set;

public interface BeanResolver {

    /**
     *
     * @param type
     * @return
     */
    Set<Bean<?>> introspect(Class<?> type) throws IntrospectException;

}
