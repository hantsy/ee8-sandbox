/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@Named("scopeBean")
public class ScopeBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ScopeBean.class.getName());

    @Inject
    @Push
    PushContext applicationChannel;

    @Inject
    @Push
    PushContext sessionChannel;

    @Inject
    @Push
    PushContext viewChannel;

    @Inject
    @Push
    PushContext userChannel;

    public void pushToApplicationChannel() {
        applicationChannel.send("sent to applicationChannel at ::" + LocalDateTime.now());
    }

    public void pushToSessionChannel() {
        sessionChannel.send("sent to sessionChannel at ::" + LocalDateTime.now());
    }

    public void pushToViewChannel() {
        viewChannel.send("sent to viewChannel at ::" + LocalDateTime.now());
    }

    public void pushToUserChannel() {
        userChannel.send("sent to userChannel at ::" + LocalDateTime.now(), "user");
    }

     public void pushToMultiUsersChannel() {
        userChannel.send("sent to userChannel at ::" + LocalDateTime.now(), Arrays.asList("user", "hantsy"));
    }
}
