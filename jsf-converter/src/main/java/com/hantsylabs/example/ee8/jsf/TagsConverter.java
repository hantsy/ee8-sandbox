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
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@FacesConverter(value = "tagsConverter", managed = true)
public class TagsConverter implements Converter<List<String>> {
    
    @Inject
    ConverterUtils utils;

    @Override
    public List<String> getAsObject(FacesContext context, UIComponent component, String value) {
       return utils.stringToList(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, List<String> value) {
       return utils.listToString(value);
    }

}
