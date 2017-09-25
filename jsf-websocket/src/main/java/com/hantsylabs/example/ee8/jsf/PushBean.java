/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Level;
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
@Named("pushBean")
public class PushBean implements Serializable {
    
    private final Logger log = Logger.getLogger(PushBean.class.getName());
    
    @Inject
    @Push
    PushContext push;
    
    String message;
    
    public void sendMessage() {
        log.log(Level.INFO, "send push message");
        this.sendPushMessage("hello");
    }
    
    private void sendPushMessage(Object message) {
        push.send("" + message + " at " + LocalDateTime.now());
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void sendMessage2() {
       // log.log(Level.INFO, "send push message from input box::" + this.message);
        this.sendPushMessage(this.message);
    }
    
}
