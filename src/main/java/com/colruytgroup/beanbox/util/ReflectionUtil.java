package com.colruytgroup.beanbox.util;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtil {

    /**
     * Resolves the type closure of a given class
     *
     * @param type the type to get the type closure for
     * @return a set containing the whole class hierarchy excluding Object.class
     */
    public static Set<Type> resolveTypeClosure(Class<?> type) {
        return resolveTypeClosure(type, new HashSet<>());
    }

    /**
     * Recursive method to fetch type closure
     *
     * @param child the type to get the closure for
     * @param closureSet the set to add all classes to
     * @return a set containing the whole class hierarchy excluding Object.class
     */
    private static Set<Type> resolveTypeClosure(Class<?> child, Set<Type> closureSet) {
        closureSet.addAll(Arrays.asList(child.getInterfaces()));
        if (child.getSuperclass() != Object.class) {
            return resolveTypeClosure(child.getSuperclass());
        }
        return closureSet;
    }

    /**
     * Helper method so we don't have to suppress unchecked casts everywhere
     *
     * @param o the object to be cast
     * @return o as type T
     * @param <T> the type
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }

}
