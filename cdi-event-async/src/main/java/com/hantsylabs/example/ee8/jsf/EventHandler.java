/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class EventHandler implements Serializable {

    public static final Logger LOG = Logger.getLogger(EventHandler.class.getName());

    public void onMessage(@ObservesAsync Message message) {
        LOG.log(Level.INFO, "observes event:{0}", message);
    }
}
