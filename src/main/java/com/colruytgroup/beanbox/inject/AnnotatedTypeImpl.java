package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

public class AnnotatedTypeImpl<T> extends AbstractAnnotated implements AnnotatedType<T> {

    private final Class<T> type;
    private final Set<AnnotatedConstructor<T>> constructors;
    private final Set<AnnotatedMethod<? super T>> methods;
    private final Set<AnnotatedField<? super T>> fields;

    public AnnotatedTypeImpl(Class<T> type,
                             Set<Type> typeClosure,
                             Set<Annotation> annotations,
                             Set<AnnotatedConstructor<T>> constructors,
                             Set<AnnotatedMethod<? super T>> methods,
                             Set<AnnotatedField<? super T>> fields) {
        super(typeClosure, annotations);
        this.type = type;
        this.constructors = constructors;
        this.methods = methods;
        this.fields = fields;
    }

    @Override
    public Class<T> getJavaClass() {
        return type;
    }

    @Override
    public Set<AnnotatedConstructor<T>> getConstructors() {
        return constructors;
    }

    @Override
    public Set<AnnotatedMethod<? super T>> getMethods() {
        return methods;
    }

    @Override
    public Set<AnnotatedField<? super T>> getFields() {
        return fields;
    }

    @Override
    public Type getBaseType() {
        return Type.class;
    }

}
