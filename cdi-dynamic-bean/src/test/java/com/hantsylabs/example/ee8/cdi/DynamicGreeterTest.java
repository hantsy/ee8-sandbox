/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import java.util.Set;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.Extension;
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
public class DynamicGreeterTest {

    @Deployment(name = "test")
    public static Archive<?> createDeployment() {

        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        Greeter.class,
                        Resources.class,
                        CdiConfig.class
                )
                .addAsServiceProvider(Extension.class, CdiConfig.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/test-beans.xml", "beans.xml");
                
        // System.out.println(archive.toString(true));
        return archive;
    }

    @Inject
    Logger LOG;

    @Inject
    Greeter greeter;

    @Test()
    public void testInvalidBean() {
        Set<Bean<?>> greeters = CDI.current().getBeanManager().getBeans(Greeter.class);
        assertTrue(greeters.size() == 1);
        
        assertNotNull(greeter);
    }
}
