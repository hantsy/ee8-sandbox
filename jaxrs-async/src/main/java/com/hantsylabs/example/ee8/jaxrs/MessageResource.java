/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * 
 * Copied from AsyncResponse javadoc.
 *
 * @author hantsy
 */
@Path("/messages/next")
public class MessageResource {

    private static final BlockingQueue<AsyncResponse> suspended
            = new ArrayBlockingQueue<AsyncResponse>(5);

    @GET
    public void readMessage(@Suspended AsyncResponse ar) throws InterruptedException {
        suspended.put(ar);
    }

    @POST
    public String postMessage(final String message) throws InterruptedException {
        final AsyncResponse ar = suspended.take();
        ar.resume(message); // resumes the processing of one GET request
        return "Message sent";
    }
}
