/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class IterableBean {
    
    @Inject Logger LOG;

    private Iterable data = Arrays.asList("javaee 8", "jsf 2.3");

    public Iterable getData() {
        LOG.log(Level.INFO, "called IterableBean.getData");
        return data;
    }

}
