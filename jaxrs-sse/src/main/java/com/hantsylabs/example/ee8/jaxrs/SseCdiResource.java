/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
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
@Path("cdievents")
@RequestScoped
public class SseCdiResource {

//    @GET
//    @Produces(MediaType.SERVER_SENT_EVENTS)
//    public void eventStreamCdi(@Context Sse sse, @Context SseEventSink eventSink, @Observes Message msg) {
//        eventSink.send(
//                sse.newEventBuilder()
//                        .mediaType(MediaType.APPLICATION_JSON_TYPE)
//                        .id(UUID.randomUUID().toString())
//                        .name("message from cdi")
//                        .data(msg)
//                        .build()
//        );
//    }
//    
}
