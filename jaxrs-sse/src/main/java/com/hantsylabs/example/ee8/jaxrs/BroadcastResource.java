/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author hantsy
 */
@Path("broadcastEvents")
@Singleton
public class BroadcastResource {

    @Context
    private Sse sse;

    private volatile SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init() {
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    //@Path("register")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context SseEventSink eventSink) {
        eventSink.send(sse.newEvent("welcome!"));
        sseBroadcaster.register(eventSink);
    }

    @POST
    //@Path("broadcast")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void broadcast(@FormParam("event") String event) {
        sseBroadcaster.broadcast(sse.newEvent(event));
    }

    public void eventStreamCdi(@Observes Message msg) {
        sseBroadcaster.broadcast(
                sse.newEventBuilder()
                        .mediaType(MediaType.APPLICATION_JSON_TYPE)
                        .id(UUID.randomUUID().toString())
                        .name("message from cdi")
                        .data(msg)
                        .build()
        );
    }
}
