/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.AjaxBehaviorEvent;
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
@Named("ajaxBean")
public class AjaxBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AjaxBean.class.getName());
    
    @Inject
    @Push
    PushContext ajaxChannel;

    @Inject
    @Push
    PushContext ajaxListenerChannel;

    @Inject
    @Push
    PushContext commandScriptChannel;

    private List<String> messages = new ArrayList<>();

    public void ajaxPushed(AjaxBehaviorEvent e) {
        LOG.log(Level.INFO, "ajax pushed: " + e.toString());
        
        messages.add("ajaxListenerEvent is sent at: " + LocalDateTime.now());
        
        ajaxListenerChannel.send("ajaxListenerEvent");
    }

    public void commandScriptExecuted() {
        LOG.log(Level.INFO, "commandScriptExecuted pushed.");
        
        messages.add("commandScriptExecuted message is sent at: " + LocalDateTime.now());
        
        commandScriptChannel.send("onCommandScript");
    }
    
    public void sendMessage() {
//        LOG.log(Level.INFO, "ajax pushed by button: " + e.toString());
        
        messages.add("ajaxEvent is sent at: " + LocalDateTime.now());
        
        ajaxChannel.send("ajaxEvent");
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
