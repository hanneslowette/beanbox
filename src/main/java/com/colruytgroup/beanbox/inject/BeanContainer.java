package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.Bean;
import java.lang.annotation.Annotation;
import java.util.Set;

public interface BeanContainer {

    /**
     * Lookup a set of beans that qualify for the given name
     * @param name the name of the bean to look for
     * @return a set of beans with the given name or an empty set when none are found
     */
    Set<Bean<?>> forName(String name);

    /**
     * Lookup a set of beans that qualify for the given parameters
     *
     * @param type the type the bean needs to be of
     * @param annotations the qualifier annotations
     * @return a set of beans that qualify for the given annotations
     */
    Set<Bean<?>> lookup(Class<?> type, Annotation... annotations);

    /**
     * Resolves a set of beans to the most qualified one
     * @param beans The set of beans to be filtered
     * @return The most qualified bean
     * @param <T> The bean type
     */
    <T> Bean<? extends T> resolve(Set<Bean<? extends T>> beans);

    /**
     * Attempts to register a bean
     *
     * @param bean The bean to be registered
     */
    void register(Bean<?> bean);

    /**
     * Checks whether or not this container contains a bean of this type
     *
     * @param type The type of bean to look for
     * @return true when a bean of this type is present in this container
     */
    boolean exists(Class<?> type);
}
