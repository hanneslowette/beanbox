package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.enterprise.inject.spi.Annotated;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 *
 */
public abstract class AbstractAnnotated implements Annotated {

    private final Set<Annotation> annotations;
    private final Set<Type> typeClosure;

    public AbstractAnnotated(Set<Type> typeClosure, Set<Annotation> annotations) {
        this.annotations = annotations;
        this.typeClosure = typeClosure;
    }

    @Override
    public Set<Type> getTypeClosure() {
        return typeClosure;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> aClass) {
        for (Annotation annotation : annotations) {
            if (annotation.getClass() == aClass) {
                return aClass.cast(annotation);
            }
        }
        return null;
    }

    @Override
    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> aClass) {
        return this.getAnnotation(aClass) != null;
    }

}
