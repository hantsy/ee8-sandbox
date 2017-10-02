/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.event.WebsocketEvent.Closed;
import javax.faces.event.WebsocketEvent.Opened;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class WebsocketObserver {

    @Inject
    Logger LOG;

    public void onOpen(@Observes @Opened WebsocketEvent opened) {
        LOG.log(Level.INFO, "event opend::{0}", opened);
    }

    public void onClose(@Observes @Closed WebsocketEvent closed) {
        LOG.log(Level.INFO, "event closed::{0}", closed);
    }

}
