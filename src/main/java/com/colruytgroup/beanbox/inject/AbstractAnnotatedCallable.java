package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedCallable;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public abstract class AbstractAnnotatedCallable<BaseClass extends Member, T> extends AbstractAnnotatedMember<BaseClass, T> implements AnnotatedCallable<T> {

    private List<AnnotatedParameter<T>> parameters;

    public AbstractAnnotatedCallable(Set<Type> typeClosure, Set<Annotation> annotations, BaseClass member, AnnotatedType<T> parent, List<AnnotatedParameter<T>> parameters) {
        super(typeClosure, annotations, member, parent);
        this.parameters = parameters;
    }

    @Override
    public List<AnnotatedParameter<T>> getParameters() {
        return null;
    }
}
