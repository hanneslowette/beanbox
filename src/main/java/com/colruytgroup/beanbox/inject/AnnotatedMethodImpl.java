package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class AnnotatedMethodImpl<T> extends AbstractAnnotatedCallable<Method, T> implements AnnotatedMethod<T> {

    public AnnotatedMethodImpl(Set<Type> typeClosure, Set<Annotation> annotations, Method member, AnnotatedType<T> parent, List<AnnotatedParameter<T>> parameters) {
        super(typeClosure, annotations, member, parent, parameters);
    }

    @Override
    public Method getJavaMember() {
        return super.member;
    }

    @Override
    public Type getBaseType() {
        return Method.class;
    }

}
