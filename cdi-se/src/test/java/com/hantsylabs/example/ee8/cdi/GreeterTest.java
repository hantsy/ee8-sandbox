/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import java.util.Set;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.Extension;
import org.jboss.weld.environment.se.ContainerLifecycleObserver;
import static org.jboss.weld.environment.se.ContainerLifecycleObserver.afterBeanDiscovery;
import static org.jboss.weld.environment.se.ContainerLifecycleObserver.processAnnotatedType;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.exceptions.UnsatisfiedResolutionException;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author hantsy
 */
public class GreeterTest {

    public GreeterTest() {
    }

    SeContainer container;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bootBasicContainer() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            assertTrue(container.isRunning());
            Set<Bean<?>> greeters = container.getBeanManager().getBeans("greeter");
            assertTrue(greeters.size() == 1);
        }
    }

    @Test
    public void bootstrapWeldContainer() {
        Weld weld = new Weld();

        try (WeldContainer container = weld.initialize()) {
            Greeter greeter = container.select(Greeter.class).get();
            assertTrue(greeter != null);
        }
    }

    @Test(expected = UnsatisfiedResolutionException.class)
    public void bootWeldSeContainer() {
        Extension testExtension = ContainerLifecycleObserver.extensionBuilder()
            .add(afterBeanDiscovery((e) -> System.out.println("Bean discovery completed!")))
            .add(processAnnotatedType().notify((e) -> {
                if (e.getAnnotatedType().getJavaClass().getName().startsWith("com.hantsylab")) {
                    e.veto();
                }
            })).build();
        try (WeldContainer container = new Weld().addExtension(testExtension).initialize()) {
            Greeter greeter = container.select(Greeter.class).get();
            //assertTrue(greeter == null);
        }

    }
}
