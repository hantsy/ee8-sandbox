/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hantsy
 */
@ViewScoped
@Named("eventBean")
public class EventBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(EventBean.class.getName());

    @Inject
    @Push
    PushContext eventChannel;

    public void sendMessage() {
        eventChannel.send("event message was sent at::" + LocalDateTime.now());
    }
}
