/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author hantsy
 */
@Named
@ApplicationScoped
public class Greeter {

    public void say(String name) {
        System.out.println("Hi, " + name);
    }
}
