/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hantsy
 */
@RunWith(Arquillian.class)
public class FooTest {

    @Deployment(name = "test")
    public static Archive<?> createDeployment() {

        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        Counted.class,
                        Counter.class,
                        CountedInterceptor.class,
                        Foo.class,
                        Resources.class
                )
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/test-beans.xml", "beans.xml");
        // System.out.println(archive.toString(true));
        return archive;
    }

    @Inject
    Logger LOG;

    @Inject
    Foo foo;

    @Inject
    Counter counter;

    @Test()
    public void testGreeter() {
        assertNotNull(foo);

        LOG.log(Level.INFO, "counter.getCount()::" + counter.getCount());
        //assertTrue(1 == counter.getCount());
        foo.bar();
        LOG.log(Level.INFO, "counter.getCount() after called foo.bar()::" + counter.getCount());
        assertTrue(2 == counter.getCount());
    }
}
