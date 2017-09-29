/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import static java.lang.String.join;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class ConverterUtils {

    public String listToString(List tags) {
        return join(",", tags);
    }

    public List stringToList(String str) {
        return Arrays.asList(str.split(","));
    }
}
