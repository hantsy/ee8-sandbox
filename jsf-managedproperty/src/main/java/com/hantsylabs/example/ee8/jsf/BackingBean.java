/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import javax.enterprise.inject.Model;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class BackingBean {

    @Inject
    @ManagedProperty("#{fooBean.bar}")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
       
}
