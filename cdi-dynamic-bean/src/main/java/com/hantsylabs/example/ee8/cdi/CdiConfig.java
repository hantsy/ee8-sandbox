/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

/**
 *
 * @author hantsy
 */
public class CdiConfig implements Extension{

    public void addABean(@Observes AfterBeanDiscovery event) {
        // get an instance of BeanConfigurator
        event.addBean()
                // set the desired data
                .types(Greeter.class)
                .scope(ApplicationScoped.class)
                .addQualifier(Default.Literal.INSTANCE)
                //.addQualifier(Custom.CustomLiteral.INSTANCE);
                //finally, add a callback to tell CDI how to instantiate this bean
                .produceWith(obj -> new Greeter());
    }
}
