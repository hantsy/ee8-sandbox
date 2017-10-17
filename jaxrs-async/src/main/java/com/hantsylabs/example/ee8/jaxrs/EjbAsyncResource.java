/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
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
@Path("ejb")
@Stateless
public class EjbAsyncResource {

    @Inject
    Logger LOG;

    @GET
    @Asynchronous
    public void getAsync(final @Suspended AsyncResponse res) {

        //perform long run operations.
        try {
            LOG.log(Level.INFO, " execute long run task in EjbAsyncResource");
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            LOG.log(Level.SEVERE, "error :" +ex.getMessage());
        }

        res.resume(Response.ok("Asynchronus EJB resource").build());
    }

}
