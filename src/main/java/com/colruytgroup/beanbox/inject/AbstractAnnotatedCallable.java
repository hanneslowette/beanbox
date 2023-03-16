package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedCallable;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractAnnotatedCallable<BaseClass extends Member, T> extends AbstractAnnotatedMember<BaseClass, T> implements AnnotatedCallable<T> {

    private final List<AnnotatedParameter<T>> parameters;

    public AbstractAnnotatedCallable(Type baseType, Set<Annotation> annotations, BaseClass member, AnnotatedType<T> parent) {
        super(baseType, annotations, member, parent);
        this.parameters = new LinkedList<>();
    }

    @Override
    public List<AnnotatedParameter<T>> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "AbstractAnnotatedCallable{" +
                "annotations=" + getAnnotations() +
                "parameters=" + parameters +
                ", member=" + member +
                '}';
    }
}
