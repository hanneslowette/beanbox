package com.colruytgroup.beanbox;

import com.colruytgroup.beanbox.annotation.Transactional;
import com.colruytgroup.beanbox.exception.BeanBoxException;
import com.colruytgroup.beanbox.exception.BeanBoxRuntimeException;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;

public class BeanBoxRunner extends BlockJUnit4ClassRunner {

    public BeanBoxRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        try {
            BeanBox.initialize(target);
        } catch (BeanBoxException e) {
            throw new BeanBoxRuntimeException("could not start beanbox runner", e);
        }
        return super.withBefores(method, target, statement);
    }

    @Override
    public void run(final RunNotifier notifier) {
        final RunListener listener = new RunListener() {
            @Override
            public void testStarted(final Description description) {
                if (isTransactional(description)) {
                    Transactional test = description.getAnnotation(Transactional.class);
                    assert test != null;
                    BeanBox.beginTransaction(test.unitNames());
                }
            }


            @Override
            public void testFinished(final Description description) {
                if (isTransactional(description)) {
                    Transactional test = description.getAnnotation(Transactional.class);
                    assert test != null;
                    BeanBox.commitTransaction(test.unitNames());
                }
            }

        };

        notifier.addListener(listener);
        super.run(notifier);
        notifier.removeListener(listener);
    }

    @Override
    protected Statement withAfters(FrameworkMethod method, Object target, Statement statement) {
        return super.withAfters(method, target, statement);
    }

    private boolean isTransactional(Description description) {
        for (Annotation annotation : description.getAnnotations()) {
            if (annotation.annotationType().equals(Transactional.class)) {
                return true;
            }
        }
        return false;
    }

}
