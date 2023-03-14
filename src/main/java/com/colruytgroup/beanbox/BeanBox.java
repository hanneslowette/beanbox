package com.colruytgroup.beanbox;

import java.util.concurrent.atomic.AtomicReference;

public class BeanBox {

    /**
     *
     */
    private static final AtomicReference<BeanBox> INSTANCE = new AtomicReference<>();

    /**
     *
     * @param currentContextFactory
     * @return the initialized container
     */
//    public static BeanBox initialize(CurrentContextFactory currentContextFactory) {
//        ArcContainerImpl container = INSTANCE.get();
//        if (container == null) {
//            synchronized (INSTANCE) {
//                container = INSTANCE.get();
//                if (container == null) {
//                    // Set the container instance first because Arc.container() can be used within ArcContainerImpl.init()
//                    container = new ArcContainerImpl(currentContextFactory);
//                    INSTANCE.set(container);
//                    container.init();
//                }
//            }
//        }
//        return container;
//    }

}