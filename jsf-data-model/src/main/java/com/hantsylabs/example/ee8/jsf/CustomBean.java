/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class CustomBean {

    @Inject
    Logger LOG;

    public UserData getUserData() {
        LOG.log(Level.INFO, "called CustomBean.getUserData");

        List<User> data = IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .mapToObj(i -> new User("first " + i, "last " + i))
                .collect(Collectors.toList());

        return new UserData(data);
    }

}
