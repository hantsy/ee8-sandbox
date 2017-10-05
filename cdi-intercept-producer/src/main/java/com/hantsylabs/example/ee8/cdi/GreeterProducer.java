/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InterceptionFactory;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class GreeterProducer {

    @Produces
    public Greeter producesGreeter(InterceptionFactory<Greeter> factory) {
        factory.configure().add(Counted.Literal.INSTANCE);
        return factory.createInterceptedInstance(new Greeter());
    }

}
