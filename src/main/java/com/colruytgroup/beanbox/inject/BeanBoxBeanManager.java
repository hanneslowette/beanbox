package com.colruytgroup.beanbox.inject;

import com.colruytgroup.beanbox.context.PersistenceBeanContext;
import com.colruytgroup.beanbox.exception.BeanBoxRuntimeException;
import com.colruytgroup.beanbox.exception.IntrospectException;
import com.colruytgroup.beanbox.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.*;
import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanBoxBeanManager extends AbstractBeanManager {

    private final Logger logger = LoggerFactory.getLogger(BeanBoxBeanManager.class);
    private final Map<Class<? extends Annotation>, Context> contextMap;
    private final BeanContainer container;
    private final PersistenceBeanContext persistenceContext;

    public BeanBoxBeanManager() {
        this.contextMap = new HashMap<>();
        this.container = new BeanContainerImpl();
        this.persistenceContext = new PersistenceBeanContext();
    }

    /**
     * Registers a context
     *
     * @param context the context to register
     */
    public void registerContext(Context context) {
        if (contextMap.containsKey(context.getScope())) {
            throw new IllegalStateException("duplicate scope detected");
        }
        this.contextMap.put(context.getScope(), context);
    }

    /**
     * Introspects a class, meaning all annotated types will be discovered that the
     * given type depends on as well as creating beans for the respective annotated
     * objects
     *
     * @param annotated the type to be introspected
     */
    public <T> Bean<T> introspect(AnnotatedType<T> annotated) {
        logger.debug("found bean for type {}", annotated.getJavaClass());
        DefaultBean<T> bean = new DefaultBean<>(annotated, this);
        bean.getInjectionPoints().addAll(InjectionPoints.discoverFieldInjectionPoints(annotated, bean));
        container.register(bean);

        for (AnnotatedField<? super T> field : annotated.getFields()) {
            Class<?> type = ReflectionUtil.cast(field.getBaseType());

            if (!container.exists(type)) {
                introspect(this.createAnnotatedType(type));
            }
        }
        return bean;
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> set) {
        return container.resolve(set);
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> aClass) {
        return AnnotatedHelper.createAnnotatedType(aClass);
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        Set<Bean<?>> qualifiedBeans = this.getBeans(annotatedType.getBaseType());
        Bean<T> bean = ReflectionUtil.cast(this.resolve(qualifiedBeans));

        try {
            return InjectionTargets.createInjectionTarget(bean, annotatedType, this);
        } catch (IntrospectException e) {
            throw new BeanBoxRuntimeException("could not create injection target for " + annotatedType.getJavaClass(), e);
        }
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
        return new DummyCreationalContext<>();
    }

    @Override
    public Object getReference(Bean<?> bean, Type type, CreationalContext<?> creationalContext) {
        Context context = this.contextMap.get(bean.getScope());
        if (context == null) {
            throw new BeanBoxRuntimeException("no context found for scope " + bean.getScope());
        }
        return context.get(bean);
    }

    @Override
    public Object getInjectableReference(InjectionPoint injectionPoint, CreationalContext<?> creationalContext) {
        Set<Bean<?>> qualifiedBeans = this.getBeans(injectionPoint.getType(), injectionPoint.getQualifiers().toArray(new Annotation[0]));
        Bean<?> selected = this.resolve(qualifiedBeans);

        if (selected == null) {
            throw new BeanBoxRuntimeException("bean not found for " + injectionPoint.getType());
        }

        return this.getReference(selected, injectionPoint.getType(), creationalContext);
    }

    @Override
    public Set<Bean<?>> getBeans(Type type, Annotation... annotations) {
        return container.lookup((Class<?>) type, annotations);
    }

    @Override
    public Set<Bean<?>> getBeans(String s) {
        return container.forName(s);
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> annotation) {
        return true; // all scopes are normal scopes in this implementation
    }

    @Override
    public boolean isScope(Class<? extends Annotation> annotation) {
        return contextMap.containsKey(annotation);
    }

    @Override
    public Context getContext(Class<? extends Annotation> scope) {
        return this.contextMap.get(scope);
    }

    public EntityManager getPersistenceReference(String unitName) {
        return persistenceContext.create(unitName);
    }

}
