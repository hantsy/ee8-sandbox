/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class EventHandler implements Serializable {

    public static final Logger LOG = Logger.getLogger(EventHandler.class.getName());

    public void onMessage(@ObservesAsync @Priority(value = 1) Message message) {
        LOG.info("observes event:" + message);
    }

    public void onAnotherMessage(@ObservesAsync @Priority(value = 2) Message message) {
        LOG.info("observes event in the second order:" + message);
    }
}
