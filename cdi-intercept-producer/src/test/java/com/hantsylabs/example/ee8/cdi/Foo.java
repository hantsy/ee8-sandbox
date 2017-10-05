/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.cdi;

import javax.enterprise.context.ApplicationScoped;



/**
 *
 * @author hantsy
 */
@Counted
@ApplicationScoped
public class Foo {

    public void bar() {
        System.out.print("calll bar method of Foo.");
    }

}
