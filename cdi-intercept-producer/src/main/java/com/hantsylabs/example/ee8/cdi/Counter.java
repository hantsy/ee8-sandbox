/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class Counter {
    
    private int count = 0;
    
    @Inject
    Logger LOG;
    
    public void increase() {        
        count++;
        LOG.log(Level.INFO, "current counter:" + count);
    }
    
    public int getCount() {
        return count;
    }
    
}
