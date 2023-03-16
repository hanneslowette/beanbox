package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedMember;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Set;

public abstract class AbstractAnnotatedMember<BaseClass extends Member, T> extends AbstractAnnotated implements AnnotatedMember<T> {

    protected final BaseClass member;
    private final AnnotatedType<T> parent;

    public AbstractAnnotatedMember(Type baseType, Set<Annotation> annotations, BaseClass member, AnnotatedType<T> parent) {
        super(baseType, parent.getTypeClosure(), annotations);
        this.member = member;
        this.parent = parent;
    }

    @Override
    public Member getJavaMember() {
        return member;
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(member.getModifiers());
    }

    @Override
    public AnnotatedType<T> getDeclaringType() {
        return parent;
    }

    @Override
    public String toString() {
        return "AbstractAnnotatedMember{" +
                "annotations=" + getAnnotations() +
                "member=" + member +
                '}';
    }
}
