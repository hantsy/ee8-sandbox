/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author hantsy
 */
@Path("mesages")
@RequestScoped
public class MessageResource {
    
    @Inject
    Event<Message> messageEvent;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response send(Message message) {
        messageEvent.fire(message);
        return Response.ok().build();
    }
}
