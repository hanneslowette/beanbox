package com.colruytgroup.beanbox.bean;

import com.colruytgroup.beanbox.exception.IntrospectException;

import java.util.Set;

public class TypeSafeBeanResolver implements BeanResolver {

    @Override
    public Set<Bean<?>> introspect(Class<?> type) throws IntrospectException {

        return null;
    }

}
