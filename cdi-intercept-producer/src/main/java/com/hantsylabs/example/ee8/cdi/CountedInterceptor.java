/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author hantsy
 */
@Interceptor
@Counted
public class CountedInterceptor {

    @Inject
    Logger LOG;

    @Inject
    Counter counter;

    @AroundConstruct
    public void aroundConstructed(InvocationContext ctx) throws Exception {
        LOG.log(Level.INFO, "invoke constructor:" + ctx.getConstructor() + ", arguments:" + ctx.getContextData());
        Object o = ctx.proceed();
        counter.increase();
    }

    @AroundInvoke
    public Object aroundInvoked(InvocationContext ctx) throws Exception {
        LOG.log(Level.INFO, "invoke method:" + ctx.getMethod() + ", paraemters:" + ctx.getParameters());
        Object o = ctx.proceed();
        counter.increase();

        return o;
    }

}
