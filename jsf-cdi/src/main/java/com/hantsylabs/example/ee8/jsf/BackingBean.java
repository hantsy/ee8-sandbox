/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.Map;
import javax.enterprise.inject.Model;
import javax.faces.annotation.ViewMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.RequestCookieMap;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class BackingBean {

    // JSF 2.2
    //FacesContext context= FacesContext.getCurrentInstance();
    //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    //Map<String, Object> cookieMap = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
    //Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    @RequestCookieMap
    Map<String, Object> cookieMap;
    
    @Inject
    @ViewMap
    Map<String, Object> viewMap;

}
