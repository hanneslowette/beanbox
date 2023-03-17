package com.colruytgroup.beanbox.context;

import com.colruytgroup.beanbox.inject.DefaultBean;
import com.colruytgroup.beanbox.inject.DummyCreationalContext;
import com.colruytgroup.beanbox.util.ReflectionUtil;

import javax.ejb.Stateless;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class StatelessContext implements Context {

    private final Map<Contextual<?>, Object> beanCache;
    private final BeanManager manager;

    public StatelessContext(BeanManager manager) {
        this.manager = manager;
        this.beanCache = new HashMap<>();
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        return get(contextual);
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        if (beanCache.containsKey(contextual)) {
            return ReflectionUtil.cast(beanCache.get(contextual));
        }

        DefaultBean<T> bean = ReflectionUtil.cast(contextual);
        InjectionTarget<T> target = manager.createInjectionTarget(bean.getType());
        T instance = target.produce(new DummyCreationalContext<T>());
        beanCache.put(contextual, instance);

        target.inject(instance, new DummyCreationalContext<T>());
        target.postConstruct(instance);

        return instance;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Stateless.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
