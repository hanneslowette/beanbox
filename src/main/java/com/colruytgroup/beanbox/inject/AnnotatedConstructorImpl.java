package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class AnnotatedConstructorImpl<T> extends AbstractAnnotatedCallable<Constructor<T>, T> implements AnnotatedConstructor<T> {

    public AnnotatedConstructorImpl(Set<Type> typeClosure, Set<Annotation> annotations, Constructor<T> member, AnnotatedType<T> parent, List<AnnotatedParameter<T>> parameters) {
        super(typeClosure, annotations, member, parent, parameters);
    }

    @Override
    public Constructor<T> getJavaMember() {
        return super.member;
    }

    @Override
    public Type getBaseType() {
        return Constructor.class;
    }
}
