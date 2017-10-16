/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

/**
 *
 * @author hantsy
 */
public class Client {

    public final static void main(String[] args) {

        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/jaxrs-sse/rest/events");

        SseEventSource eventSource = SseEventSource.target(target).build();

        // EventSource#register(Consumer<InboundSseEvent>)
        // Registered event handler will print the received message.
        eventSource.register(System.out::println);

        // Subscribe to the event stream.
        eventSource.open();
    }
}
