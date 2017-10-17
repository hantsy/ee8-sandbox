/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author hantsy
 */
@Path("async")
@Stateless
public class AsyncResource {

    @Resource
    private ManagedExecutorService executor;

    @Inject
    Logger LOG;

    @GET
    public void getAsync(final @Suspended AsyncResponse res) {
        res.setTimeoutHandler(
                (ar) -> {
                    ar.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                            .entity("Operation timed out --- please try again.").build());
                }
        );
        res.setTimeout(1000, TimeUnit.MILLISECONDS);
        executor.submit(() -> {
            //do long run operations.
            try {
                LOG.log(Level.INFO, " execute long run task in AsyncResource");
                //Thread.sleep(new Random().nextInt(1005));
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, "error :" + ex.getMessage());
            }
            res.resume(Response.ok("asynchronous resource").build());
        });
    }

}
