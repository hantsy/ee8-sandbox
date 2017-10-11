/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author hantsy
 */
@WebListener
public class WebInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FacesContext.getCurrentInstance()
                .getApplication()
                .addSearchKeywordResolver(new GrandParentKeywordResolver());

    }
}
