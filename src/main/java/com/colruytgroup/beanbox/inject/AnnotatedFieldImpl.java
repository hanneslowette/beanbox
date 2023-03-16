package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Set;

public class AnnotatedFieldImpl<T> extends AbstractAnnotatedMember<Field, T> implements AnnotatedField<T> {

    public AnnotatedFieldImpl(Set<Type> typeClosure, Set<Annotation> annotations, Field member, AnnotatedType<T> parent) {
        super(typeClosure, annotations, member, parent);
    }

    @Override
    public Field getJavaMember() {
        return super.member;
    }

    @Override
    public Type getBaseType() {
        return Field.class;
    }
}
