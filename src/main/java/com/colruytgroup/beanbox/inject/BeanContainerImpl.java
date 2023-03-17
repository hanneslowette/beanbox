package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.InjectionException;
import javax.enterprise.inject.spi.Bean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;

/**
 * The bean container
 */
public class BeanContainerImpl implements BeanContainer {

    private final Map<Type, Bean<?>> beans;

    public BeanContainerImpl() {
        this.beans = new HashMap<>();
    }

    @Override
    public void register(Bean<?> bean) {
        beans.put(bean.getBeanClass(), bean);
    }

    @Override
    public Set<Bean<?>> lookup(Type type, Annotation... annotations) {
        if (annotations.length > 0) {
            Set<Bean<?>> qualifiedBeans = new HashSet<>();
            Bean<?> bean = beans.get(type);
            for (Annotation annotation : annotations) {
                if (bean.getQualifiers().contains(annotation)) {
                    qualifiedBeans.add(bean);
                }
            }
            return qualifiedBeans;
        } else {
            return Collections.<Bean<?>>singleton(beans.get(type));
        }

    }

    @Override
    public Set<Bean<?>> forName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        Set<Bean<?>> qualifiedBeans = new HashSet<>();
        for (Bean<?> bean : beans.values()) {
            if (bean.getName().equals(name)) {
                qualifiedBeans.add(bean);
            }
        }
        return qualifiedBeans;
    }

    @Override
    public <T> Bean<? extends T> resolve(Set<Bean<? extends T>> beans) {
        if (beans.size() == 0) {
            return null;
        } else if (beans.size() == 1) {
            return beans.iterator().next();
        }
        throw new InjectionException();
    }

}
