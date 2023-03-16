package com.colruytgroup.beanbox;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.List;

public class BeanBoxRunner extends BlockJUnit4ClassRunner {

    public BeanBoxRunner(Class<?> testClass) throws InitializationError {
        super(testClass);

        System.out.println("instantiated");
    }

    @Override
    protected void validateFields(List<Throwable> errors) {
        System.out.println("validate fields");
        super.validateFields(errors);
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        System.out.println("withBefores");
        return super.withBefores(method, target, statement);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        System.out.println("computeTestMethods");
        return super.computeTestMethods();
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return super.methodInvoker(method, test);
    }

}
