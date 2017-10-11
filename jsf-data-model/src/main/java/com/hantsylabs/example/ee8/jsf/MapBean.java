/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class MapBean {

    @Inject
    Logger LOG;

    private Map<Integer, String> data = new HashMap<>();

    public Map<Integer, String> getData() {
        LOG.log(Level.INFO, "called MapBean.getData");
        data.put(1, "java ee 8");
        data.put(2, "jsf 2.3");
        return data;
    }

}
