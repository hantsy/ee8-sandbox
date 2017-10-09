/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.event.PostRenderViewEvent;
import javax.faces.event.PostValidateEvent;
import javax.faces.event.PreRenderComponentEvent;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.event.PreValidateEvent;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class PostRenderViewBean {

    @Inject
    Logger LOG;

    public void init(PreRenderViewEvent e) {
        LOG.log(Level.INFO, "fire PreRenderViewEvent:" + e);
    }

    public void execute() {
        LOG.log(Level.INFO, "execute viewAction");
    }

    public void initialized(PostRenderViewEvent e) {
        LOG.log(Level.INFO, "fire PostRenderViewEvent:" + e);
    }

//    public void postAddToView(PostAddToViewEvent e) {
//        LOG.log(Level.INFO, "fire PostAddToViewEvent:" + e);
//    }
//
//    public void preValidate(PreValidateEvent e) {
//        LOG.log(Level.INFO, "fire PreValidateEvent:" + e);
//    }
//
//    public void postValidate(PostValidateEvent e) {
//        LOG.log(Level.INFO, "fire PostValidateEvent:" + e);
//    }
//
//    public void preRenderComponentEvent(PreRenderComponentEvent e) {
//        LOG.log(Level.INFO, "fire PreRenderComponentEvent:" + e);
//    }

}
