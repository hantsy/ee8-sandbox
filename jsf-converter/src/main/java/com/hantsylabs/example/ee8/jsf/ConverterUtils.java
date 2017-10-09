/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import static java.lang.String.join;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class ConverterUtils {

    @Inject
    Logger LOG;

    public String listToString(List<String> value) {
        LOG.log(Level.INFO, "listToString:{0}", value);
        return value != null ? join(",", value) : "";
    }

    public List<String> stringToList(String value) {
        LOG.log(Level.INFO, "stringToList:{0}", value);
        return value != null ? Arrays.asList(value.split(",")) : Collections.<String>emptyList();
    }

}
