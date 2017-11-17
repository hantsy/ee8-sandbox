/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class EventHandler implements Serializable {

    public static final Logger LOG = Logger.getLogger(EventHandler.class.getName());

    public void onMessage(@Observes @Priority(value = 1) Message message) {
        LOG.log(Level.INFO, "observes event with @Priority(value = 1):{0}", message);
    }

    public void onAnotherMessage(@Observes @Priority(value = 2) Message message) {
        LOG.log(Level.INFO, "observes event with @Priority(value = 2):{0}", message);
    }
}
