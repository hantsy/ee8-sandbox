/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * see: https://www.infoq.com/news/2017/08/JAX-RS-2.1-released
 *
 * @author hantsy
 */
@Path("cdievents")
@RequestScoped
public class SseCdiResource {

    @Inject
    MessageHandler handler;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventStreamCdi(@Context Sse sse, @Context SseEventSink eventSink) {
        handler.register(UUID.randomUUID().toString(), new SseRequest(sse, eventSink));
    }
    
    
    @DELETE
    @Path("{uuid}")
    public void deregister(@PathParam("uuid") String uuid) {
        handler.deregister(uuid);
    }

}
