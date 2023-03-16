package com.colruytgroup.beanbox;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(BeanBoxRunner.class)
public class BeanBoxTest {

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @Test
    public void testsomething() {
        System.out.println("test something");
    }

    @Test
    public void testsomethingelse() {
        System.out.println("test something else ");
    }

    @Test
    public void testsomethingelsfffe() {
        System.out.println("test something elsffff ");
    }

}
