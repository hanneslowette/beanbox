package com.colruytgroup.beanbox.bean.inject;

import javax.enterprise.inject.spi.Annotated;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 *
 */
public abstract class AbstractAnnotated implements Annotated {

    /**
     *
     */
    private final Class<?> baseClass;

    /**
     *
     */
    private final Set<Annotation> annotations;



    @Override
    public Type getBaseType() {
        return null;
    }

    @Override
    public Set<Type> getTypeClosure() {
        return null;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> aClass) {
        return null;
    }

    @Override
    public Set<Annotation> getAnnotations() {
        return null;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> aClass) {
        return false;
    }

}
