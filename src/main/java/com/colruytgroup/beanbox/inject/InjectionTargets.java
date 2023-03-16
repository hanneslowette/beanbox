package com.colruytgroup.beanbox.inject;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.InjectionTarget;

public final class InjectionTargets {

    private InjectionTargets(){}

    public static <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        return null;
    }

}
