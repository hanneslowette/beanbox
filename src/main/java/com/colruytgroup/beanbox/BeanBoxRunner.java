package com.colruytgroup.beanbox;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class BeanBoxRunner extends BlockJUnit4ClassRunner {

    public BeanBoxRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return super.methodInvoker(method, test);
    }

}
