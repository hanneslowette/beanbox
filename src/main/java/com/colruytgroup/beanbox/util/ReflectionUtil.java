package com.colruytgroup.beanbox.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
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
        Set<Type> hierarchy = new HashSet<>();
        for (Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass()) {
            hierarchy.add(c);
            hierarchy.addAll(resolveInterfaceClosure(type, hierarchy));
        }
        return hierarchy;
    }

    /**
     * Resolves the interface closure for a given class
     *
     * @param type The type to get the closure for
     * @param hierarchy The set containing the superclass' current hierarchy
     * @return
     */
    private static Set<Type> resolveInterfaceClosure(Class<?> type, Set<Type> hierarchy) {
        if (type.getInterfaces().length == 0) {
            return hierarchy;
        }
        hierarchy.addAll(Arrays.asList(type.getInterfaces()));
        for (Class<?> iface : type.getInterfaces()) {
            resolveInterfaceClosure(iface, hierarchy);
        }
        return hierarchy;
    }

    /**
     * Resolves all annotations including the ones from the parent classes and interfaces
     *
     * @param annotatedClass The class to resolve the hierarchy for
     * @return The set of annotations
     */
    public static Set<Annotation> resolveAnnotations(Class<?> annotatedClass) {
        Set<Annotation> annotations = new HashSet<>();
        for (Type type : resolveTypeClosure(annotatedClass)) {
            annotations.addAll(Arrays.asList(ReflectionUtil.<Class<?>>cast(type).getAnnotations()));
        }
        return annotations;
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
