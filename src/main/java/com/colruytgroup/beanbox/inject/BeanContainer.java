package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.exception.BeanBoxException;

import javax.enterprise.inject.spi.Bean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public interface BeanContainer {

    /**
     * Lookup a set of beans that qualify for the given name
     * @param name
     * @return
     */
    Set<Bean<?>> forName(String name);

    /**
     * Lookup a set of beans that qualify for the given parameters
     *
     * @param type the type the bean needs to be of
     * @param annotations the qualifier annotations
     * @return a set of beans that qualify for the given annotations
     */
    Set<Bean<?>> lookup(Type type, Annotation... annotations);

    /**
     *
     * @param beans
     * @return
     * @param <T>
     */
    <T> Bean<? extends T> resolve(Set<Bean<? extends T>> beans);

    /**
     *
     * @param bean
     */
    void register(Bean<?> bean);

}
