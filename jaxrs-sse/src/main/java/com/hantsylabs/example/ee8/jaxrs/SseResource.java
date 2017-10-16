/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("events")
@RequestScoped
public class SseResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getMessageQueue(@Context Sse sse, @Context SseEventSink eventSink) {
        // Resource method is invoked when a client subscribes to an event stream.
        // That implies that sending events will most likely happen from different
        // context - thread / event handler / etc, so common implementation of the
        // resource method will store the eventSink instance and the application 
        // logic will retrieve it when an event should be emitted to the client.

        // sending events:
        eventSink.send(sse.newEvent("event1"));
    }
}
