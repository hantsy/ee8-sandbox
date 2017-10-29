/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jaxrs;

import java.io.Serializable;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author hantsy
 */
public class SseRequest implements Serializable {

    private Sse sse;
    private SseEventSink eventSink;

    public SseRequest(Sse sse, SseEventSink eventSink) {
        this.sse = sse;
        this.eventSink = eventSink;
    }
    
    public Sse getSse() {
        return sse;
    }

    public void setSse(Sse sse) {
        this.sse = sse;
    }

    public SseEventSink getEventSink() {
        return eventSink;
    }

    public void setEventSink(SseEventSink eventSink) {
        this.eventSink = eventSink;
    }

}
