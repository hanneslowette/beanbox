package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedCallable;
import javax.enterprise.inject.spi.AnnotatedParameter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Set;

public class AnnotatedParameterImpl<T> extends AbstractAnnotated implements AnnotatedParameter<T> {

    private final int position;
    private final AnnotatedCallable<T> declaringCallable;

    public AnnotatedParameterImpl(Set<Type> typeClosure, Set<Annotation> annotations, int position, AnnotatedCallable<T> declaringCallable) {
        super(typeClosure, annotations);

        this.position = position;
        this.declaringCallable = declaringCallable;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public AnnotatedCallable<T> getDeclaringCallable() {
        return this.declaringCallable;
    }

    @Override
    public Type getBaseType() {
        return Parameter.class;
    }

}
